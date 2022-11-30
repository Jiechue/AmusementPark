package com.jiechu.springboot.controller.DTO;

import lombok.Data;

@Data
public class FacilityCategoryQueryDTO {
    private Integer currentPage;
    private Integer pageNum;
    private Integer pageSize;
    private String name;
}
