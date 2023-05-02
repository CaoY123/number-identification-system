package com.mine.cni.dao;

import com.mine.cni.domain.ImageFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CaoY
 * @date 2023-05-02 20:45
 * @description ImageFile DAO层
 */
@Mapper
public interface ImageFileDAO {

    List<ImageFile> findAll();

    ImageFile findById(int id);

    List<ImageFile> findByPath(String path);

    boolean update(ImageFile imageFile);

    boolean insert(ImageFile imageFile);

    boolean deleteById(int id);


}
