package com.jiechu.springboot.service.impl;

import com.jiechu.springboot.controller.DTO.UserQueryDTO;
import com.jiechu.springboot.dao.UserDao;
import com.jiechu.springboot.entity.User;
import com.jiechu.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User login(String username, String password) {
        return userDao.findUser(username,password);
    }

    @Override
    public boolean registerUser(User user) {
        if (userDao.insertUser(user)>0)
            return true;
        return false;
    }

    @Override
    public List<User> showAllUser() {
        return userDao.finAllUsers();
    }

    @Override
    public boolean deleteUser(Integer id) {
        if (userDao.deleteUser(id)>0)
            return true;
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        if (userDao.updateUser(user)>0)
            return true;
        return false;
    }

    @Override
    public List<User> showPageUser(UserQueryDTO userQueryDTO) {
        return userDao.findPageUsers(userQueryDTO);
    }

    @Override
    public long count(UserQueryDTO userQueryDTO) {
        return userDao.count(userQueryDTO);
    }
}
