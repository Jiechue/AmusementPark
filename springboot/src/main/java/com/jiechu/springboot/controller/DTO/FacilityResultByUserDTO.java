package com.jiechu.springboot.controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FacilityResultByUserDTO {
    private Integer id;
    private String category;
    private String name;
    private String opentime;
    private Double price;
    private String description;
    private boolean enable;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate createtime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate updatetime;
    private String cover;
    private String height;
    private String age;
    private boolean show;
    private Integer liketotal;
    private boolean Like;
}
