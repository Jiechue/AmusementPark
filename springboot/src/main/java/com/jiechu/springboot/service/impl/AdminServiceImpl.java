package com.jiechu.springboot.service.impl;

import com.jiechu.springboot.common.SecurePassword;
import com.jiechu.springboot.controller.DTO.AdminQueryDTO;
import com.jiechu.springboot.controller.DTO.AdminUpdatePasswordDTO;
import com.jiechu.springboot.controller.DTO.LoginDTO;
import com.jiechu.springboot.dao.AdminDao;
import com.jiechu.springboot.entity.Admin;
import com.jiechu.springboot.entity.User;
import com.jiechu.springboot.exception.ServiceException;
import com.jiechu.springboot.service.AdminService;
import com.jiechu.springboot.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public LoginDTO login(String username, String password) {
        Admin admin = adminDao.findAdmin(username, SecurePassword.securePassword(password));
        if (admin == null){
            throw new ServiceException("用户名或密码错误");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin,loginDTO);
        System.out.println(loginDTO);
        //生成token
        String token = TokenUtils.getToken(String.valueOf(admin.getId()),admin.getPassword());
        loginDTO.setToken(token);
        return loginDTO;
    }

    @Override
    public boolean registerAdmin(Admin admin) {
        admin.setPassword(SecurePassword.securePassword(admin.getPassword()));
        if (adminDao.insertAdmin(admin)>0)
            return true;
        return false;
    }

    @Override
    public List<Admin> showAllAdmins() {
        return adminDao.finAllAdmins();
    }

    @Override
    public boolean deleteAdmin(Integer id) {
        if (adminDao.deleteAdmin(id)>0)
            return true;
        return false;
    }


    @Override
    public boolean updateAdmin(Admin admin) {
        if (adminDao.updateAdmin(admin)>0)
            return true;
        return false;
    }

    @Override
    public List<Admin> showPageAdmin(AdminQueryDTO adminQueryDTO) {
        return adminDao.findPageAdmins(adminQueryDTO);
    }

    @Override
    public long count(AdminQueryDTO adminQueryDTO) {
        return adminDao.count(adminQueryDTO);
    }

    @Override
    public Admin showAdminById(Integer id) {
        return adminDao.findAdimById(id);
    }

    @Override
    public boolean updateAdminPassword(AdminUpdatePasswordDTO adminUpdatePasswordDTO) {
        adminUpdatePasswordDTO.setNewPassword(SecurePassword.securePassword(adminUpdatePasswordDTO.getNewPassword()));
        if (adminDao.updateAdminPassword(adminUpdatePasswordDTO)>0)
            return true;
        return false;
    }

}
