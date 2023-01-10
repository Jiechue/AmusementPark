package com.jiechu.springboot.controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiechu.springboot.entity.User;
import lombok.Data;

import java.time.LocalDate;
@Data
public class MessageResultByUserDTO {
    private Integer id;
    private Integer liketotal;
    private Integer disliketotal;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDate createtime;
    private String content;
    private User user;
    private Integer facilityid;
    private boolean like;
    private boolean dislike;
}
