package com.mine.cni.enums;

import lombok.Getter;

/**
 * @author CaoY
 * @date 2023-05-02 21:45
 * @description 识别码枚举
 */
@Getter
public enum RecogCodeEnums {
    SUCCESS(1, "识别成功"),
    FAILURE(0, "识别失败"),

    ;

    private Integer code;
    private String desc;

    RecogCodeEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
