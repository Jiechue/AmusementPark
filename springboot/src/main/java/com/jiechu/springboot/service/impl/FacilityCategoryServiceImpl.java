package com.jiechu.springboot.service.impl;

import com.jiechu.springboot.controller.DTO.FacilityCategoryQueryDTO;
import com.jiechu.springboot.dao.FacilityCategoryDao;
import com.jiechu.springboot.entity.FacilityCategory;
import com.jiechu.springboot.service.FacilityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class FacilityCategoryServiceImpl implements FacilityCategoryService {
    @Autowired
    private FacilityCategoryDao facilityCategoryDao;
    @Override
    public boolean insertFacilityCategory(FacilityCategory facilityCategory) {
        if (facilityCategoryDao.insertFacilityCategory(facilityCategory)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<FacilityCategory> showAllFacilitiesCategories() {
        return facilityCategoryDao.finAllFacilityCategories();
    }

    @Override
    public boolean deleteFacilityCategory(Integer id) {
        if (facilityCategoryDao.deleteFacilityCategory(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateFacilityCategory(FacilityCategory facilityCategory) {
        facilityCategory.setUpdatetime(LocalDate.now());

        if (facilityCategoryDao.updateFacilityCategory(facilityCategory)>0){
            return true;
        }
        return false;
    }

    @Override
    public FacilityCategory showFacilityCategoryById(Integer id) {
        return facilityCategoryDao.findFacilityCategoryById(id);
    }

    @Override
    public List<FacilityCategory> showPageFacilityCategory(FacilityCategoryQueryDTO facilityCategoryQueryDTO) {
        System.out.println(facilityCategoryQueryDTO);
        //判断查询框是否有字符
        if (!facilityCategoryQueryDTO.getName().equals("")){
            List<FacilityCategory> list = facilityCategoryDao.selectByName(facilityCategoryQueryDTO.getName());
            Set<Integer> pid = new HashSet<>();
            //把获取到的pid去重
            for (int i = 0;i<list.size();i++){
                if (list.get(i).getPid()!=null){
                    pid.add(list.get(i).getPid());
                }
            }

            //判断它是否有pid
            if (pid.size()!=0){
                //hashSet转ArrayList
                List<Integer> plist = new ArrayList<>(pid);
                //初始化最终输出的List
                List<FacilityCategory> pidlist = new ArrayList<>();

                for (int i=0;i<plist.size();i++){
                    //添加当前分类
                    pidlist.add(facilityCategoryDao.findFacilityCategoryById(plist.get(i)));

                    List<FacilityCategory> l = new ArrayList<>();
                    //循环查询到的数据，如果它的是当前分类的二级分类，就加入进list中
                    for (int j=0;j<list.size();j++){
                        if (list.get(i).getPid()==plist.get(i)){
                            l.add(list.get(i));
                        }
                    }
                    pidlist.get(i).setChildren(l);
                }
                return pidlist;
            }
            return facilityCategoryDao.findPageFacilityCategories(facilityCategoryQueryDTO);
        }
        return facilityCategoryDao.findPageFacilityCategories(facilityCategoryQueryDTO);
    }

    @Override
    public long count(FacilityCategoryQueryDTO facilityCategoryQueryDTO) {
        return facilityCategoryDao.count(facilityCategoryQueryDTO);
    }
}
