package com.jiechu.springboot.dao;

import com.jiechu.springboot.controller.DTO.MessageQueryDTO;
import com.jiechu.springboot.entity.Facility;
import com.jiechu.springboot.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao {
    int insert(Message message);
    List<Message> selectAll();
    List<Message> selectAllByFacilityId(Integer id);
    int delete(Integer id);
    List<Message> selectPageFacilities(MessageQueryDTO messageQueryDTO);
    long count(MessageQueryDTO messageQueryDTO);
    Message selectById(@Param("id")Integer id);
    int updateAddLike(@Param("id")Integer id);
    int updateAddDisLike(@Param("id")Integer id);
    int updateReduceLike(@Param("id")Integer id);
    int updateReduceDisLike(@Param("id")Integer id);
}
