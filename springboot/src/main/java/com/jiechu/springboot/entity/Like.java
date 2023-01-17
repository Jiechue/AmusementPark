package com.jiechu.springboot.entity;

import lombok.Data;

@Data
public class Like {
    private Integer userid;
    private Integer targetid;
    private Integer action;
}
