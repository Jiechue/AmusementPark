package com.jiechu.springboot.dao;

import com.jiechu.springboot.entity.Home;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeDao {
    int update(Home home);
}
