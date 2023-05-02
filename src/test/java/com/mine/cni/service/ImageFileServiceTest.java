package com.mine.cni.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.RandomUtil;
import com.mine.cni.domain.ImageFile;
import com.mine.cni.enums.DateTimeFormatterEnums;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CaoY
 * @date 2023-05-02 21:08
 * @description
 */
@SpringBootTest
@TestPropertySource(properties = {"jasypt.encryptor.password=${MY_SECRET_PASSWORD}"})
public class ImageFileServiceTest {

    @Autowired
    private ImageFileService imageFileService;

    private ImageFile imageFile;

    @BeforeEach
    public void before() {
        this.imageFile = new ImageFile();
        imageFile.setCode(RandomUtil.randomString("abcdefghijklmnopqrstuvwxyz", 11));
        imageFile.setPath(RandomUtil.randomString("abcdefghijklmnopqr\\st", 13));
        imageFile.setFlag(RandomUtil.randomInt(0, 2));
        imageFile.setUploadTime(LocalDateTimeUtil.format(LocalDateTime.now(), DateTimeFormatterEnums.YYYY_MM_DD_HH_MM_SS.getPattern()));
    }

    // 一下接口测试均通过
    @Test
    public void test() {
//        boolean saveResult = imageFileService.save(imageFile);
//        System.out.println("保存结果：" + saveResult);

        ImageFile imageFile1 = imageFileService.findById(1);
        System.out.println(imageFile1);

        List<ImageFile> allImageFile = imageFileService.findAll();
        System.out.println("总记录数： " + allImageFile.size());

        List<ImageFile> imageFile2 = imageFileService.findByPath("ggimbsbgohkml");
        System.out.println(imageFile2);

//        imageFile2.get(0).setFlag(imageFile2.get(0).getFlag() == 1 ? 0 : 1);
//        boolean updateResult = imageFileService.update(imageFile2.get(0));
//        System.out.println("更新结果：" + updateResult);

//        boolean deleteResult = imageFileService.delete(3);
//        System.out.println("删除结果：" + deleteResult);
    }

}
