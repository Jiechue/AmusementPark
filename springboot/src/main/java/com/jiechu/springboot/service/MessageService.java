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
    boolean addLike(Message message);
    boolean addDisLike(Message message);
    boolean reduceLike(Message message);
    boolean reduceDisLike(Message message);
}
