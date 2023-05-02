package com.mine.cni;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CaoY
 * @date 2023-05-02 15:41
 * @description python 内核调用程序
 */
public class PythonCoreCaller {

    private static String condaEnvironmentName;
    private static String condaExecutablePath;
    private static  String pythonScriptPath;
    private static String workingDirectoryPath;
    static {
        // conda 虚拟环境的名称
        condaEnvironmentName = System.getenv("CONDA_ENVIRONMENT_NAME");
        // conda 的 命令执行文件，可能是 conda.bat 的绝对路径名
        condaExecutablePath = System.getenv("CONDA_EXECUTABLE_PATH");
        // 工作目录，也就是识别编号项目的绝对路径
        workingDirectoryPath = System.getenv("WORKING_DIRECTORY_PATH");
        // 要执行的主脚本的绝对路径，这个脚本串起了整个执行流程
        pythonScriptPath = workingDirectoryPath + File.separator + "main.py";
    }

    public static String run(String imgPath) {

        List<String> command = new ArrayList<>(Arrays.asList(
                condaExecutablePath,
                "run",
                "-n",
                condaEnvironmentName,
                "python",
                pythonScriptPath,
                "--source",
                imgPath
        ));

        File workingDirectory = new File(workingDirectoryPath);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        processBuilder.directory(workingDirectory);

        String resultStr = "";
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));

            String line;
            String resultPrefix = "RESULT:";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith(resultPrefix)) {
                    resultStr = line.substring(resultPrefix.length()).trim();
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Python script exited with code: " + exitCode);
            System.out.println("Result string: " + resultStr);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return resultStr;
    }
}
