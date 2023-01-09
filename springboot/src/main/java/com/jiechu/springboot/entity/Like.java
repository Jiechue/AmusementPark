package com.jiechu.springboot.entity;

import lombok.Data;

@Data
public class Like {
    private Integer userid;
    private Integer messageid;
    private Integer action;
}
