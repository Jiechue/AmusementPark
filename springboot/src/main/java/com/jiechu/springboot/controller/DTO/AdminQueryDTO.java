package com.jiechu.springboot.controller.DTO;

import lombok.Data;

@Data
public class AdminQueryDTO {
    private Integer currentPage;
    private Integer pageNum;
    private Integer pageSize;
    private String username;
}
