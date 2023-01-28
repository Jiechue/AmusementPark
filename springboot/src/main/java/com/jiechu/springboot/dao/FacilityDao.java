package com.jiechu.springboot.dao;

import com.jiechu.springboot.controller.DTO.FacilityQueryDTO;
import com.jiechu.springboot.entity.Facility;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FacilityDao {
    int insert(Facility facility);
    List<Facility> selectAll();
    int delete(Integer id);
    int update(Facility facility);
    List<Facility> selectPage(FacilityQueryDTO facilityQueryDTO);
    long count(FacilityQueryDTO facilityQueryDTO);
    Facility selectById(@Param("id") Integer id);
    List<Facility> selectReception(Facility facility);
    int updateAddTotalLike(@Param("id")Integer id);
    int updateReduceTotalLike(@Param("id")Integer id);
    List<Facility> selectReceptionHome();
}
