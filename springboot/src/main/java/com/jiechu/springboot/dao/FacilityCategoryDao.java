package com.jiechu.springboot.dao;


import com.jiechu.springboot.controller.DTO.FacilityCategoryQueryDTO;
import com.jiechu.springboot.entity.FacilityCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface FacilityCategoryDao {
    int insertFacilityCategory(FacilityCategory facilityCategory);
    List<FacilityCategory> finAllFacilityCategories();
    int deleteFacilityCategory(Integer id);
    int updateFacilityCategory(FacilityCategory facilityCategory);
    FacilityCategory findFacilityCategoryById(@Param("id") Integer id);
    List<FacilityCategory> findPageFacilityCategories(FacilityCategoryQueryDTO facilityCategoryQueryDTO);
    long count (FacilityCategoryQueryDTO facilityCategoryQueryDTO);
    List<FacilityCategory> selectByName(@Param("name") String name);
}
