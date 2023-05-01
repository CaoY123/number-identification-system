package com.mine.cni.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.mine.cni.dao.UserDAO;
import com.mine.cni.domain.User;
import com.mine.cni.enums.DateTimeFormatterEnums;
import com.mine.cni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            // 更新用户信息
            User oldUser = userDAO.findById(user.getId());
            if (oldUser == null) {
                return null;
            }
            return userDAO.update(user) ? userDAO.findById(user.getId()) : null; // 更新成功则返回user，失败则返回null
        } else {
            // 新用户进来，对密码进行加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.insert(user);
            return user.getId() != null ? userDAO.findById(user.getId()) : null; // 保存成功则返回user，失败则返回null
        }
    }

    @Override
    public User handleLogin(String name, String password) {
        User user = userDAO.findByName(name);
        if (user != null) {
            // 查出来后将两个密码匹配一下
            boolean matchesResult = passwordEncoder.matches(password, user.getPassword());
            if (!matchesResult) {
                // 密码错误
                return null;
            }
            user.setLastLoginTime(LocalDateTimeUtil.format(LocalDateTime.now(), DateTimeFormatterEnums.YYYY_MM_DD_HH_MM_SS.getPattern()));
            userDAO.update(user);
        }
        return user;
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public boolean updatePassword(int id, String newPass) {
        User user = new User();
        user.setId(id);
        // 加密后存储数据库
        user.setPassword(passwordEncoder.encode(newPass));
        return userDAO.update(user);
    }

    @Override
    public boolean delete(int id) {
        User user = userDAO.findById(id);
        if (user == null) {
            return false;
        }

        return userDAO.deleteById(id);
    }

}
