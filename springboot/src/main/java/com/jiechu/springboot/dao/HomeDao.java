package com.jiechu.springboot.dao;

import com.jiechu.springboot.entity.Home;
import com.jiechu.springboot.entity.HomeImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeDao {
    int update(Home home);
    int deleteImg();
    int insertImg(@Param("url")String url);
    Home select();
    List<HomeImage> selectImages();
}
