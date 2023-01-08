package com.jiechu.springboot.dao;

import com.jiechu.springboot.controller.DTO.UpdatePasswordDTO;
import com.jiechu.springboot.controller.DTO.UserQueryDTO;
import com.jiechu.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper //注入进容器
public interface UserDao {
    User findUser(@Param("username") String username,@Param("password") String password);
    int insertUser(User user);
    List<User> finAllUsers();
    int deleteUser(Integer id);
    int updateUser(User user);
    List<User> findPageUsers(UserQueryDTO userQueryDTO);
    long count(UserQueryDTO userQueryDTO);
    User selectById(@Param("id") Integer id);
    int updatePassword(UpdatePasswordDTO updatePasswordDTO);
    int resetPassword(User user);
}
