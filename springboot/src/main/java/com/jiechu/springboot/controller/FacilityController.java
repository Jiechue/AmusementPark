package com.jiechu.springboot.controller;

import com.jiechu.springboot.common.Result;
import com.jiechu.springboot.controller.DTO.FacilityCategoryQueryDTO;
import com.jiechu.springboot.controller.DTO.FacilityQueryDTO;
import com.jiechu.springboot.entity.Facility;
import com.jiechu.springboot.entity.FacilityCategory;
import com.jiechu.springboot.exception.ServiceException;
import com.jiechu.springboot.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/facility")
public class FacilityController {
    @Autowired
    FacilityService facilityService;
    @GetMapping("/page")  // /user/page?currentPage=1&pageSize=20
    public Result findPageFacilitiesCategories(FacilityQueryDTO facilityQueryDTO){
        Integer currentPage = facilityQueryDTO.getCurrentPage();
        Integer pageSize = facilityQueryDTO.getPageSize();

        if (currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        facilityQueryDTO.setPageNum(pageNum);

        List<Facility> facilities = facilityService.showPageFacilities(facilityQueryDTO);
        long total = facilityService.count(facilityQueryDTO);

        Map<String,Object> map = new HashMap<>();
        map.put("list",facilities);
        map.put("total",total);
        return Result.success(map);

        //使用pageHelper
//        PageHelper.startPage(currentPage,pageSize);
//        List<User> users = userService.showPageUser(userQueryDTO);
//        return Result.success(new PageInfo<>(users));

    }
    @PostMapping
    public Result add(@RequestBody Facility facility){
        System.out.println(facility.toString());
        if (facility.getName() == null){
            return Result.error("参数错误");
        }
        return Result.success(facilityService.addFacility(facility));
    }
    @PutMapping
    public Result update(@RequestBody Facility facility){
        if (facility.getName() == null){
            return Result.error("参数错误");
        }
        return Result.success(facilityService.updateFacility(facility));
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (id==null){
            return Result.error("参数错误");
        }
        return Result.success(facilityService.deleteFacility(id));
    }

}
