package com.jiechu.springboot.controller;

import com.jiechu.springboot.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/action")
public class ActionController {
    @PostMapping("/like")
    public Result like(){
        return Result.success();
    }
    @PostMapping("/hate")
    public Result hate(){
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (id==null){
            return Result.error("参数错误");
        }
        return Result.success();
    }
}
