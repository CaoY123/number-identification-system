package com.mine.cni.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author CaoY
 * @date 2023-04-30 21:48
 * @description 角色枚举
 */
@Getter
public enum RoleEnums {

    NORMAL(0, "普通用户"),
    MANAGER(1, "管理员"),
    ;

    private Integer code;
    private String desc;

    RoleEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
