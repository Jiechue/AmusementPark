package com.jiechu.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.jiechu.springboot.controller.DTO.FacilityQueryDTO;
import com.jiechu.springboot.dao.FacilityDao;
import com.jiechu.springboot.entity.Facility;
import com.jiechu.springboot.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {
    @Autowired
    private FacilityDao facilityDao;
    @Override
    public boolean addFacility(Facility facility) {
        if (facilityDao.insert(categoriesToCategory(facility))>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Facility> showAllFacilities() {
        return facilityDao.selectAll();
    }

    @Override
    public boolean deleteFacility(Integer id) {
        if (facilityDao.delete(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateFacility(Facility facility) {
        if (facilityDao.update(categoriesToCategory(facility))>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Facility> showPageFacilities(FacilityQueryDTO facilityQueryDTO) {
        return facilityDao.selectPage(facilityQueryDTO);
    }

    @Override
    public long count(FacilityQueryDTO facilityQueryDTO) {
        return facilityDao.count(facilityQueryDTO);
    }

    @Override
    public Facility showFacilityById(Integer id) {
        return facilityDao.selectById(id);
    }

    @Override
    public List<Facility> showReception(Facility facility) {
        return facilityDao.selectReception(categoriesToCategory(facility));
    }

    @Override
    public boolean addLikeTotal(Integer id) {
        if (facilityDao.updateAddTotalLike(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean reduceLikeTotal(Integer id) {
        if (facilityDao.updateReduceTotalLike(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Facility> showReceptionHome() {
        return facilityDao.selectReceptionHome();
    }

    private Facility categoriesToCategory(Facility facility){
        List<String> categories = facility.getCategories();
        StringBuilder sb = new StringBuilder();
        if (CollUtil.isNotEmpty(categories)){
            categories.forEach(v -> sb.append(v).append(" > "));
            facility.setCategory(sb.toString().substring(0, sb.lastIndexOf(" > ")));
        }
        return facility;
    }

}
