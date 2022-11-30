package com.jiechu.springboot.controller.DTO;

import lombok.Data;

@Data
public class LoginDTO {
    private Integer id;
    private String username;
    private String token;
}
