package com.mine.cni.domain;

import lombok.Data;

/**
 * @author CaoY
 * @date 2023-05-02 20:43
 * @description 集装箱图片类
 */
@Data
public class ImageFile {
    // 主键id
    private Integer id;
    // 绝对路径
    private String path;
    // 集装箱变化
    private String code;
    // 识别结果是否正确，暂时认为识别的结果都正确
    private Integer flag;
    // 上传时间 格式： yyyy-MM-dd HH:mm:ss
    private String uploadTime;
}
