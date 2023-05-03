package com.mine.cni.controller.image;

import com.mine.cni.controller.user.CommonUserController;
import com.mine.cni.domain.ImageFile;
import com.mine.cni.domain.User;
import com.mine.cni.domain.base.JsonResult;
import com.mine.cni.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author CaoY
 * @date 2023-05-03 16:47
 * @description
 */
@RestController
@RequestMapping("/imagefiles")
public class ImageFileController extends CommonUserController {

    @Autowired
    private ImageFileService imageFileService;

    @GetMapping("/history")
    @ResponseBody
    public JsonResult history(HttpServletRequest request) {
        User user = getUserFromSession(request);
        if (user == null) {
            return new JsonResult(false, "请先登录");
        }

        List<ImageFile> imageFiles = imageFileService.findAll();
        JsonResult jsonResult = new JsonResult(true);
        jsonResult.put("imageFiles", imageFiles);
        return jsonResult;
    }

}
