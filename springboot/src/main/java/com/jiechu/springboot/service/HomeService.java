package com.jiechu.springboot.service;

import com.jiechu.springboot.entity.Facility;
import com.jiechu.springboot.entity.Home;
import com.jiechu.springboot.entity.HomeImage;

import java.util.List;

public interface HomeService {
    boolean update(Home home);
    boolean deleteImg();
    boolean addImg(List<String> url);
    Home load();
    List<HomeImage> loadImages();
}
