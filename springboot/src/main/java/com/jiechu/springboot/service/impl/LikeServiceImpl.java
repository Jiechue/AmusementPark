package com.jiechu.springboot.service.impl;

import com.jiechu.springboot.dao.LikeDao;
import com.jiechu.springboot.entity.Like;
import com.jiechu.springboot.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeDao likeDao;
    @Override
    public boolean add(Like like) {
        return false;
    }

    @Override
    public boolean delete(Like like) {
        return false;
    }

    @Override
    public boolean update(Like like) {
        return false;
    }

    @Override
    public Like showByUserIdAndMessageId(Integer userid,Integer messageid) {
        return likeDao.selectByUserIdAndMessageId(userid,messageid);
    }
}
