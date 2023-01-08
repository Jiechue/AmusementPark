package com.jiechu.springboot.service;

import com.jiechu.springboot.controller.DTO.AdminLoginDTO;
import com.jiechu.springboot.controller.DTO.UpdatePasswordDTO;
import com.jiechu.springboot.controller.DTO.UserLoginDTO;
import com.jiechu.springboot.controller.DTO.UserQueryDTO;
import com.jiechu.springboot.entity.User;

import java.util.List;

public interface UserService {
    UserLoginDTO login(String username, String password);
    boolean registerUser(User user);
    List<User> showAllUser();
    boolean deleteUser(Integer id);
    boolean updateUser(User user);
    List<User> showPageUser(UserQueryDTO userQueryDTO);
    long count(UserQueryDTO userQueryDTO);
    User showUserById(Integer id);
    boolean updateUserPassword(UpdatePasswordDTO updatePasswordDTO);
    boolean resetPassword(User user);
}