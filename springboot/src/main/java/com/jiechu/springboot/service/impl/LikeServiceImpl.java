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
        if (likeDao.insert(like)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Like like) {
        if (likeDao.delete(like)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Like like) {
        if (likeDao.update(like)>0){
            return true;
        }
        return false;
    }

    @Override
    public Like showByUserIdAndMessageId(Integer userid,Integer messageid) {
        return likeDao.selectByUserIdAndMessageId(userid,messageid);
    }

    @Override
    public boolean addFacility(Like like) {
        if (likeDao.insertFacility(like)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFacility(Like like) {
        if (likeDao.deleteFacility(like)>0){
            return true;
        }
        return false;
    }

    @Override
    public Like showFacilityByUserIdAndMessageId(Integer userid, Integer facilityid) {
        return likeDao.selectFacilityByUserIdAndMessageId(userid,facilityid);
    }
}
