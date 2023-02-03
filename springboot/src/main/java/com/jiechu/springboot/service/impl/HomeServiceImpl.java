package com.jiechu.springboot.service.impl;

import com.jiechu.springboot.dao.HomeDao;
import com.jiechu.springboot.entity.Home;
import com.jiechu.springboot.entity.HomeImage;
import com.jiechu.springboot.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    HomeDao homeDao;
    @Override
    public boolean update(Home home) {
        if (homeDao.update(home)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteImg() {
        if (homeDao.deleteImg()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean addImg(List<String> urls) {
        for (String url:urls){
            if (homeDao.insertImg(url)>0){

            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public Home load() {
        return homeDao.select();
    }

    @Override
    public List<HomeImage> loadImages() {
        return homeDao.selectImages();
    }
}
