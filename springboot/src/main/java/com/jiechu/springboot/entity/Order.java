package com.jiechu.springboot.entity;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private Integer userid;
    private boolean pay;
}
