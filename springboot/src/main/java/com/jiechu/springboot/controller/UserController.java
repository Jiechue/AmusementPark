package com.jiechu.springboot.controller;

import com.jiechu.springboot.common.Result;
import com.jiechu.springboot.entity.User;
import com.jiechu.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            Result.error("参数错误");
        }
        User res = userService.login(user.getUsername(),user.getPassword());
        if(res == null){
            Result.error("用户名或密码错误");
        }
        return Result.success(res);
    }
    @GetMapping("/list")
    public Result finAllUsers(){
        return Result.success(userService.showAllUser());
    }
    @PostMapping
    public Result register(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            return Result.error("参数错误");
        }
        return Result.success(userService.registerUser(user));
    }
    @PutMapping
    public Result update(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            return Result.error("参数错误");
        }
        return Result.success(userService.updateUser(user));
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (id==null){
            return Result.error("参数错误");
        }
        return Result.success(userService.deleteUser(id));
    }

}
