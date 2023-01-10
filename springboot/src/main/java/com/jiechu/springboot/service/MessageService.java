package com.jiechu.springboot.service;

import com.jiechu.springboot.controller.DTO.FacilityQueryDTO;
import com.jiechu.springboot.controller.DTO.MessageQueryDTO;
import com.jiechu.springboot.entity.Facility;
import com.jiechu.springboot.entity.Message;

import java.util.List;

public interface MessageService {
    boolean add(Message message);
    List<Message> showAll();
    List<Message> showAllByFacilityId(Integer id);
    boolean delete(Integer id);
    List<Message> showPageMessages(MessageQueryDTO messageQueryDTO);
    long count(MessageQueryDTO messageQueryDTO);
    Message showById(Integer id);
    boolean addLike(Integer id);
    boolean addDisLike(Integer id);
    boolean reduceLike(Integer id);
    boolean reduceDisLike(Integer id);
}
