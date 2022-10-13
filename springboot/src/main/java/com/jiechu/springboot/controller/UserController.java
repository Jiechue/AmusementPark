package com.jiechu.springboot.controller;

import com.jiechu.springboot.entity.User;
import com.jiechu.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public User login(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            throw new RuntimeException("参数错误");
        }
        return userService.login(user.getUsername(),user.getPassword());
    }
    @GetMapping("/list")
    public List<User> finAllUsers(){
        return userService.showAllUser();
    }
    @PostMapping("/reg")
    public String register(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            throw new RuntimeException("参数错误");
        }
        boolean isOk = userService.registerUser(user);
        if(isOk){
//            session.setAttribute("userList",userService.showAllList());
            return "userList";

        }else{
            return "userAdd";
        }
    }
    @PutMapping("/update")
    public String update(@RequestBody User user){
        userService.updateUser(user);
        return null;
    }
    @DeleteMapping("/del/{id}")
    public String delete(@PathVariable Integer id){
        userService.deleteUser(id);
        return null;
    }

}
