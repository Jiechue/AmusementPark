package com.jiechu.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Message {
    private Integer id;
    private Integer liketotal;
    private Integer disliketotal;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDate createtime;
    private String content;
    private Integer pid;
    private Integer userid;
    private Integer facilityid;
}
