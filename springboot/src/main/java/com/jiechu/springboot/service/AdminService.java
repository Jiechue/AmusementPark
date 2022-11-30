package com.jiechu.springboot.service;

import com.jiechu.springboot.controller.DTO.AdminQueryDTO;
import com.jiechu.springboot.controller.DTO.AdminUpdatePasswordDTO;
import com.jiechu.springboot.controller.DTO.LoginDTO;
import com.jiechu.springboot.controller.DTO.UserQueryDTO;
import com.jiechu.springboot.entity.Admin;
import com.jiechu.springboot.entity.User;

import java.util.List;

public interface AdminService {
    LoginDTO login(String username, String password);
    boolean registerAdmin(Admin admin);
    List<Admin> showAllAdmins();
    boolean deleteAdmin(Integer id);
    boolean updateAdmin(Admin admin);
    List<Admin> showPageAdmin(AdminQueryDTO adminQueryDTO);
    long count(AdminQueryDTO adminQueryDTO);
    Admin showAdminById(Integer id);
    boolean updateAdminPassword(AdminUpdatePasswordDTO adminUpdatePasswordDTO);
}
