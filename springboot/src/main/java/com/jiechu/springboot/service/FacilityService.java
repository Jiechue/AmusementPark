package com.jiechu.springboot.service;

import com.jiechu.springboot.controller.DTO.AdminQueryDTO;
import com.jiechu.springboot.controller.DTO.FacilityQueryDTO;
import com.jiechu.springboot.entity.Admin;
import com.jiechu.springboot.entity.Facility;

import java.util.List;

public interface FacilityService {
    boolean addFacility(Facility facilities);
    List<Facility> showAllFacilities();
    boolean deleteFacility(Integer id);
    boolean updateFacility(Facility facilities);
    List<Facility> showPageFacilities(FacilityQueryDTO facilityQueryDTO);
    long count(FacilityQueryDTO facilityQueryDTO);
    Facility showFacilityById(Integer id);
    List<Facility> showReception(Facility facility);
    boolean addLikeTotal(Facility facility);
    boolean reduceLikeTotal(Facility facility);
}
