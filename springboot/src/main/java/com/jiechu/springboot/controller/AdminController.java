package com.jiechu.springboot.controller;

import com.jiechu.springboot.common.Result;
import com.jiechu.springboot.common.SecurePassword;
import com.jiechu.springboot.controller.DTO.AdminQueryDTO;
import com.jiechu.springboot.controller.DTO.UpdatePasswordDTO;
import com.jiechu.springboot.controller.DTO.AdminLoginDTO;
import com.jiechu.springboot.entity.Admin;
import com.jiechu.springboot.exception.ServiceException;
import com.jiechu.springboot.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody Admin admin){
        if (admin.getUsername() == null||admin.getPassword()==null){
            return Result.error("参数错误");
        }
        AdminLoginDTO res = adminService.login(admin.getUsername(), admin.getPassword());
        if(res == null){
            return Result.error("用户名或密码错误");
        }
        return Result.success(res);
    }
    @GetMapping("/list")
    public Result finAllAdmins(){
        return Result.success(adminService.showAllAdmins());
    }
    @GetMapping("/page")  // /user/page?currentPage=1&pageSize=20
    public Result finPageAdmins(AdminQueryDTO adminQueryDTO){
        Integer currentPage = adminQueryDTO.getCurrentPage();
        Integer pageSize = adminQueryDTO.getPageSize();

        if (currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        adminQueryDTO.setPageNum(pageNum);

        List<Admin> users = adminService.showPageAdmin(adminQueryDTO);
        long total = adminService.count(adminQueryDTO);

        Map<String,Object> map = new HashMap<>();
        map.put("list",users);
        map.put("total",total);
        return Result.success(map);

        //使用pageHelper
//        PageHelper.startPage(currentPage,pageSize);
//        List<User> users = userService.showPageUser(userQueryDTO);
//        return Result.success(new PageInfo<>(users));

    }
    @PostMapping
    public Result register(@RequestBody Admin admin){
        if (admin.getUsername() == null||admin.getPassword()==null){
            return Result.error("参数错误");
        }
        try{
            return Result.success(adminService.registerAdmin(admin));
        }catch (DuplicateKeyException e){
            throw new ServiceException("用户名已注册");
        }
    }
    @PutMapping
    public Result update(@RequestBody Admin admin){
        if (admin.getUsername() == null||admin.getPassword()==null){
            return Result.error("参数错误");
        }
        return Result.success(adminService.updateAdmin(admin));
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (id==null){
            return Result.error("参数错误");
        }
        return Result.success(adminService.deleteAdmin(id));
    }
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody UpdatePasswordDTO adminUpdatePasswordDTO){
        System.out.println(adminUpdatePasswordDTO);
        if (adminService.showAdminById(adminUpdatePasswordDTO.getId()).getPassword().equals(SecurePassword.securePassword(adminUpdatePasswordDTO.getOldPassword()))){
            return Result.success(adminService.updateAdminPassword(adminUpdatePasswordDTO));
        }else {
            return Result.error("输入的旧密码与原密码不一致");
        }
    }
}
