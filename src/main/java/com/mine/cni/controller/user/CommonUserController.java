package com.mine.cni.controller.user;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.mine.cni.domain.User;
import com.mine.cni.domain.base.JsonResult;
import com.mine.cni.enums.DateTimeFormatterEnums;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;

public class CommonUserController {
    /**
     * 从session中获取user
     * @param request
     */
    public static User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            return (User)session.getAttribute("user");
        }
        return null;
    }

    /**
     * 检验要保存的用户数据
     * 如果合法，则设置其他默认值
     * @param user
     * @return
     */
    public static JsonResult verifyUserForm(User user) {
        if (user == null) {
            return new JsonResult(false, "操作错误");
        }

        if (user.getId() == null) {
            // 仅对新增用户进行判定
            if (StringUtils.isEmpty(user.getName())) {
                return new JsonResult(false, "用户名不能为空");
            } else if (StringUtils.isEmpty(user.getPassword())) {
                return new JsonResult(false, "密码不能为空");
            }
            user.setRole(0); // 设置默认为普通用户
            user.setLastLoginTime(LocalDateTimeUtil.format(LocalDateTime.now(), DateTimeFormatterEnums.YYYY_MM_DD_HH_MM_SS.getPattern())); // 设置最新的登录时间
        }
        return new JsonResult(true);
    }
}
