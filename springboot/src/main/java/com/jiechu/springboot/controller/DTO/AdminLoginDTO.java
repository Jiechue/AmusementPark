package com.jiechu.springboot.controller.DTO;

import lombok.Data;

@Data
public class AdminLoginDTO {
    private Integer id;
    private String username;
    private boolean power;
    private String token;
}
