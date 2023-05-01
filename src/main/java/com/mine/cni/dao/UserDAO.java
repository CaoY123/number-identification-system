package com.mine.cni.dao;

import com.mine.cni.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDAO {

    /**
     * find all user
     * excepting the password
     * @return
     */
    public List<User> findAll();

    /**
     * find user by id
     * @param id
     * @return
     */
    public User findById(int id);

    /**
     * update user
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     * insert user
     * @param user
     * @return
     */
    public boolean insert(User user);

    /**
     * delete user by id
     * @param id
     * @return
     */
    public boolean deleteById(int id);

    /**
     * find by name
     * @param name
     * @return
     */
    public User findByName(String name);
}
