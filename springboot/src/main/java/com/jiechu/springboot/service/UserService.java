package com.jiechu.springboot.service;

import com.jiechu.springboot.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User login(String username,String password);
    boolean registerUser(User user);
    List<User> showAllUser();
    boolean deleteUser(Integer id);
    boolean updateUser(User user);
}