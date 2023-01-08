package com.jiechu.springboot.service;

import com.jiechu.springboot.controller.DTO.AdminQueryDTO;
import com.jiechu.springboot.controller.DTO.UpdatePasswordDTO;
import com.jiechu.springboot.controller.DTO.AdminLoginDTO;
import com.jiechu.springboot.entity.Admin;

import java.util.List;

public interface AdminService {
    AdminLoginDTO login(String username, String password);
    boolean registerAdmin(Admin admin);
    List<Admin> showAllAdmins();
    boolean deleteAdmin(Integer id);
    boolean updateAdmin(Admin admin);
    List<Admin> showPageAdmin(AdminQueryDTO adminQueryDTO);
    long count(AdminQueryDTO adminQueryDTO);
    Admin showAdminById(Integer id);
    boolean updateAdminPassword(UpdatePasswordDTO adminUpdatePasswordDTO);
}
