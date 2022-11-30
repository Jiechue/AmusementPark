package com.jiechu.springboot.dao;

import com.jiechu.springboot.controller.DTO.AdminQueryDTO;
import com.jiechu.springboot.controller.DTO.AdminUpdatePasswordDTO;
import com.jiechu.springboot.entity.Admin;
import com.jiechu.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminDao {
    Admin findAdmin(@Param("username") String username, @Param("password") String password);
    int insertAdmin(Admin admin);
    List<Admin> finAllAdmins();
    int deleteAdmin(Integer id);
    int updateAdmin(Admin admin);
    List<Admin> findPageAdmins(AdminQueryDTO adminQueryDTO);
    long count(AdminQueryDTO adminQueryDTO);
    Admin findAdimById(@Param("id") Integer id);
    int updateAdminPassword(AdminUpdatePasswordDTO adminUpdatePasswordDTO);
}
