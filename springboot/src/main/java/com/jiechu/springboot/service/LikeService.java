package com.jiechu.springboot.service;

import com.jiechu.springboot.entity.Like;

public interface LikeService {
    boolean add (Like like);
    boolean delete (Like like);
    boolean update (Like like);
    Like showByUserIdAndMessageId(Integer userid,Integer messageid);
    boolean addFacility (Like like);
    boolean deleteFacility (Like like);
    Like showFacilityByUserIdAndMessageId(Integer userid,Integer facilityid);
}
