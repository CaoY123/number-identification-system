package com.mine.cni.service.impl;

import com.mine.cni.dao.ImageFileDAO;
import com.mine.cni.domain.ImageFile;
import com.mine.cni.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author CaoY
 * @date 2023-05-02 21:03
 * @description ImageFile 实现类
 */
@Service
public class ImageFileServiceImpl implements ImageFileService {

    @Autowired
    private ImageFileDAO imageFileDAO;


    @Override
    public List<ImageFile> findAll() {
        return imageFileDAO.findAll();
    }

    @Override
    public boolean save(ImageFile imageFile) {
        if (!Objects.isNull(imageFile)) {
            return imageFileDAO.insert(imageFile);
        }
        return false;
    }

    @Override
    public ImageFile findById(int id) {
        return imageFileDAO.findById(id);
    }

    @Override
    public List<ImageFile> findByPath(String path) {
        if (!StringUtils.isEmpty(path)) {
            return imageFileDAO.findByPath(path);
        }
        return null;
    }

    @Override
    public boolean update(ImageFile imageFile) {
        if (!ObjectUtils.isEmpty(imageFile)) {
            return imageFileDAO.update(imageFile);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return imageFileDAO.deleteById(id);
    }
}
