package com.jiechu.springboot.service.impl;

import com.jiechu.springboot.common.SecurePassword;
import com.jiechu.springboot.controller.DTO.AdminLoginDTO;
import com.jiechu.springboot.controller.DTO.UpdatePasswordDTO;
import com.jiechu.springboot.controller.DTO.UserLoginDTO;
import com.jiechu.springboot.controller.DTO.UserQueryDTO;
import com.jiechu.springboot.dao.UserDao;
import com.jiechu.springboot.entity.User;
import com.jiechu.springboot.exception.ServiceException;
import com.jiechu.springboot.service.UserService;
import com.jiechu.springboot.utils.UserTokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public UserLoginDTO login(String username, String password) {
        User user = userDao.findUser(username,SecurePassword.securePassword(password));
        if (user == null){
            throw new ServiceException("用户名或密码错误");
        }
        UserLoginDTO loginDTO = new UserLoginDTO();
        BeanUtils.copyProperties(user,loginDTO);
        System.out.println(loginDTO);
        //生成token
        String token = UserTokenUtils.getToken(String.valueOf(user.getId()),user.getPassword());
        loginDTO.setToken(token);
        return loginDTO;
    }

    @Override
    public boolean registerUser(User user) {
        user.setPassword(SecurePassword.securePassword(user.getPassword()));
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

    @Override
    public User showUserById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public boolean updateUserPassword(UpdatePasswordDTO updatePasswordDTO) {
        updatePasswordDTO.setNewPassword(SecurePassword.securePassword(updatePasswordDTO.getNewPassword()));
        if (userDao.updatePassword(updatePasswordDTO)>0)
            return true;
        return false;
    }

    @Override
    public boolean resetPassword(User user) {
        if (userDao.resetPassword(user)>0){
         return true;
        }
        return false;
    }
}
