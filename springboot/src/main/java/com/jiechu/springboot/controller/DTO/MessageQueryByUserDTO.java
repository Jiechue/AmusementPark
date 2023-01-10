package com.jiechu.springboot.controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiechu.springboot.entity.Message;
import com.jiechu.springboot.entity.User;

import java.time.LocalDate;
import java.util.List;

public class MessageQueryByUserDTO {
    private Integer id;
    private Integer liketotal;
    private Integer disliketotal;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDate createtime;
    private String content;
    private Integer pid;
    private User user;
    private Integer facilityid;
    private List<Message> children;
}
