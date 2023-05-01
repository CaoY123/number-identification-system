package com.mine.cni.domain;

import lombok.Data;

/**
 * @author CaoY
 * @date 2023-04-30 21:43
 * @description 用户类
 */
@Data
public class User {
    // 主键 id
    private Integer id;
    // 用户名
    private String name;
    // 密码
    private String password;
    // 地址
    private String address;
    // 表示用户的角色
    private Integer role;
    // 上次登录的时间
    private String lastLoginTime;
}
