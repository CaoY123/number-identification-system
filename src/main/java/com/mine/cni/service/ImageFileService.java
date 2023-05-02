package com.mine.cni.service;

import com.mine.cni.domain.ImageFile;

import java.util.List;

/**
 * @author CaoY
 * @date 2023-05-02 21:01
 * @description ImageFile 服务层
 */
public interface ImageFileService {

    List<ImageFile> findAll();

    boolean save(ImageFile imageFile);

    ImageFile findById(int id);

    List<ImageFile> findByPath(String path);

    boolean update(ImageFile imageFile);

    boolean delete(int id);
}
