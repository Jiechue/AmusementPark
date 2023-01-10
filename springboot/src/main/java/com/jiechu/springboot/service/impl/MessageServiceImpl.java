package com.jiechu.springboot.service.impl;

import com.jiechu.springboot.controller.DTO.MessageQueryDTO;
import com.jiechu.springboot.dao.FacilityDao;
import com.jiechu.springboot.dao.MessageDao;
import com.jiechu.springboot.entity.Facility;
import com.jiechu.springboot.entity.Message;
import com.jiechu.springboot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;
    @Override
    public boolean add(Message message) {
        if (messageDao.insert(message)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Message> showAll() {
        return messageDao.selectAll();
    }

    @Override
    public List<Message> showAllByFacilityId(Integer id) {
        return messageDao.selectAllByFacilityId(id);
    }

    @Override
    public boolean delete(Integer id) {
        if (messageDao.delete(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Message> showPageMessages(MessageQueryDTO messageQueryDTO) {
        return messageDao.selectPageFacilities(messageQueryDTO);
    }

    @Override
    public long count(MessageQueryDTO messageQueryDTO) {
        return messageDao.count(messageQueryDTO);
    }

    @Override
    public Message showById(Integer id) {
        return messageDao.selectById(id);
    }

    @Override
    public boolean addLike(Integer id) {
        if (messageDao.updateAddLike(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean addDisLike(Integer id) {
        if (messageDao.updateAddDisLike(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean reduceLike(Integer id) {
        if (messageDao.updateReduceLike(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean reduceDisLike(Integer id) {
        if (messageDao.updateReduceDisLike(id)>0){
            return true;
        }
        return false;
    }
}
