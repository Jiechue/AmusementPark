package com.jiechu.springboot.entity;

import lombok.Data;

import java.util.List;
@Data
public class Home {
    private String title;
    private String description;
    private String opentime;
    private List<String> pictures;
}
