package com.jiechu.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Facility {
    private Integer id;
    private String category;
    private String name;
    private String opentime;
    private Double price;
    private String description;
    private Integer enable;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate createtime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate updatetime;
    private String cover;
    private List<String> categories;
}
