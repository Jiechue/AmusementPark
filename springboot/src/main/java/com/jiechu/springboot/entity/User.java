package com.jiechu.springboot.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String sex;
    private Integer age;
    private String phone;
    private String email;
    private String address;
    private String avatar;
}
