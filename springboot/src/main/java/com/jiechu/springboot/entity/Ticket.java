package com.jiechu.springboot.entity;

import lombok.Data;

@Data
public class Ticket {
    private Integer id;
    private String name;
    private double price;
    private boolean enable;
    private String content;
    private String uncontent;
}
