package com.jiechu.springboot.controller.DTO;

import lombok.Data;

@Data
public class UserLoginDTO {
    private Integer id;
    private String username;
    private String token;
    private String avatar;
}

