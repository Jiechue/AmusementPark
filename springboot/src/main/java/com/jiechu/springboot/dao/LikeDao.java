package com.jiechu.springboot.dao;

import com.jiechu.springboot.entity.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeDao {
    int insert(Like like);
    int update(Like like);
    int delete(Like like);
    Like selectByUserIdAndMessageId(@Param("userid")Integer userid,@Param("messageid")Integer messageid);
}
