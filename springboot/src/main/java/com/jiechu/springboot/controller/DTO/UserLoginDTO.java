package com.jiechu.springboot.controller.DTO;

import lombok.Data;

@Data
public class UserLoginDTO {
    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String sex;
    private Integer age;
    private String phone;
    private String email;
    private String address;
    private String token;
    private String avatar;
}

