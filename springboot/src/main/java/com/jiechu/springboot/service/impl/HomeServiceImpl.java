package com.jiechu.springboot.service.impl;

import com.jiechu.springboot.dao.HomeDao;
import com.jiechu.springboot.entity.Home;
import com.jiechu.springboot.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
