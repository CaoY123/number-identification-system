package com.mine.cni.enums;

import lombok.Getter;

/**
 * @author CaoY
 * @date 2023-05-02 2:04
 * @description 异常类型
 */
@Getter
public enum ExceptionEnums {

    SYSTEM_EXCEPTION("0111111", "系统异常"),
    EMPTY_IMAGE_EXCEPTION("0111122", "上传的图片文件为空"),

    ;

    private String code;
    private String message;

    ExceptionEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
