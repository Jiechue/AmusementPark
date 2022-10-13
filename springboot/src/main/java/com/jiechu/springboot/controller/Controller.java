package com.jiechu.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping
public class Controller {
    @GetMapping("/{msg}")
    public String hello(@PathVariable String msg){
        return "Hello" +msg;
    }
}
