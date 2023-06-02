package com.mine.cni.controller.recognition;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.mine.cni.PythonCoreCaller;
import com.mine.cni.controller.user.CommonUserController;
import com.mine.cni.domain.ImageFile;
import com.mine.cni.domain.User;
import com.mine.cni.domain.base.JsonResult;
import com.mine.cni.enums.DateTimeFormatterEnums;
import com.mine.cni.enums.ExceptionEnums;
import com.mine.cni.enums.RecogCodeEnums;
import com.mine.cni.exception.SystemException;
import com.mine.cni.service.ImageFileService;
import com.mine.cni.utils.CheckCodeUtil;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CaoY
 * @date 2023-05-02 1:35
 * @description 识别图片的控制器
 */
@RestController
@RequestMapping("/recognize")
public class RecognitionController extends CommonUserController {

    @Autowired
    private ImageFileService imageFileService;

    // 这里直接固定上传图片的路径，是为了后面的python识别代码的方便
    private static final String UPLOAD_FOLDER = "python/container-number-identification/datasets/test";

    @GetMapping("")
    public ModelAndView work(HttpServletRequest request, HttpServletResponse response) {
        User user = getUserFromSession(request);
        if (user == null) {
            try {
                response.sendRedirect(request.getContextPath() + ""); // 重定向至登录页
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("work");
    }

    @PostMapping("/upload")
    public JsonResult upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            // 抛出图片文件为空的异常
            throw SystemException.instance(ExceptionEnums.EMPTY_IMAGE_EXCEPTION.getCode(),
                    ExceptionEnums.EMPTY_IMAGE_EXCEPTION.getMessage());
        }

        try {
            // Save the uploaded file to the server
            byte[] bytes = file.getBytes();
            Path uploadFolderPath = Paths.get(UPLOAD_FOLDER);
            if (!Files.exists(uploadFolderPath)) {
                Files.createDirectories(uploadFolderPath);
            }
//            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());

            Path filePath = uploadFolderPath.resolve(file.getOriginalFilename());
            Files.write(filePath, bytes);

            // Perform your image processing here
            System.out.println("图片处理中......................");
            String imageFilePath = filePath.toFile().getAbsolutePath();
            String result = PythonCoreCaller.run(imageFilePath);

            System.out.println("result:" + result);
            ImageFile imageFile = new ImageFile();
            imageFile.setPath(imageFilePath);
            imageFile.setCode(result);
            imageFile.setUploadTime(LocalDateTimeUtil.format(LocalDateTime.now(),
                    DateTimeFormatterEnums.YYYY_MM_DD_HH_MM_SS.getPattern()));
            if (!StringUtils.isEmpty(result)) {
                imageFile.setFlag(RecogCodeEnums.SUCCESS.getCode());
                String repairCode = CheckCodeUtil.repair(result);
                String resStr = repairCode + CheckCodeUtil.calculate(repairCode);
                imageFile.setCode(resStr);
                imageFileService.save(imageFile);
                return new JsonResult(true, "编号识别成功", result);
            } else {
                imageFile.setFlag(RecogCodeEnums.FAILURE.getCode());
                imageFileService.save(imageFile);
                return new JsonResult(false, "编号识别失败, 结果为空");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("图片处理异常");
        }
    }

    @GetMapping("/history")
    public JsonResult history(HttpServletRequest request) {

        List<ImageFile> imageFiles = imageFileService.findAll();
        JsonResult jsonResult = new JsonResult(true, "查询所哟识别记录成功！", imageFiles);
        HttpSession session = request.getSession(true);
        session.setAttribute("imageFiles", imageFiles);
        session.setMaxInactiveInterval(12 * 60 * 60); // 设置过期时间为12h
        return jsonResult;
    }
}
