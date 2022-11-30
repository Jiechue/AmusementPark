package com.jiechu.springboot.service;

import com.jiechu.springboot.controller.DTO.FacilityCategoryQueryDTO;
import com.jiechu.springboot.entity.FacilityCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FacilityCategoryService {
    boolean insertFacilityCategory(FacilityCategory facilityCategory);
    List<FacilityCategory> showAllFacilitiesCategories();
    boolean deleteFacilityCategory(Integer id);
    boolean updateFacilityCategory(FacilityCategory facilityCategory);
    FacilityCategory showFacilityCategoryById(@Param("id") Integer id);
    List<FacilityCategory> showPageFacilityCategory(FacilityCategoryQueryDTO facilityCategoryQueryDTO);
    long count(FacilityCategoryQueryDTO facilityCategoryQueryDTO);
}
