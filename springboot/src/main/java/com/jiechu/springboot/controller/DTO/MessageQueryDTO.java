package com.jiechu.springboot.controller.DTO;

import lombok.Data;

@Data
public class MessageQueryDTO {
    private Integer currentPage;
    private Integer pageNum;
    private Integer pageSize;
    private String name;
}
