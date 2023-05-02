package com.mine.cni.controller.recognition;

import com.mine.cni.controller.user.CommonUserController;
import com.mine.cni.domain.User;
import com.mine.cni.domain.base.JsonResult;
import com.mine.cni.enums.ExceptionEnums;
import com.mine.cni.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author CaoY
 * @date 2023-05-02 1:35
 * @description 识别图片的控制器
 */
@RestController
@RequestMapping("/recognize")
public class RecognitionController extends CommonUserController {

    private static final String UPLOAD_FOLDER = "uploads/";

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

            return new JsonResult(true, "图片接收成功并处理");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("图片处理异常");
        }
    }
}
