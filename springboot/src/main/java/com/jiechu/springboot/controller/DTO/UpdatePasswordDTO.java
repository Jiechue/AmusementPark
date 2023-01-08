package com.jiechu.springboot.controller.DTO;

import lombok.Data;

@Data
public class UpdatePasswordDTO {
    private Integer id;
    private String oldPassword;
    private String newPassword;
}
