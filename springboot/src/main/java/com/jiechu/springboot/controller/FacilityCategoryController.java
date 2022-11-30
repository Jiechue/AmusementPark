package com.jiechu.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import com.jiechu.springboot.common.Result;
import com.jiechu.springboot.controller.DTO.FacilityCategoryQueryDTO;
import com.jiechu.springboot.entity.FacilityCategory;
import com.jiechu.springboot.exception.ServiceException;
import com.jiechu.springboot.service.FacilityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/facilityCategory")
public class FacilityCategoryController {
    @Autowired
    private FacilityCategoryService facilityCategoryService;

    @GetMapping("/page")  // /user/page?currentPage=1&pageSize=20
    public Result findPageFacilitiesCategories(FacilityCategoryQueryDTO facilityCategoryQueryDTO){
        Integer currentPage = facilityCategoryQueryDTO.getCurrentPage();
        Integer pageSize = facilityCategoryQueryDTO.getPageSize();

        if (currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        facilityCategoryQueryDTO.setPageNum(pageNum);

        System.out.println(facilityCategoryQueryDTO);
        List<FacilityCategory> categories = facilityCategoryService.showPageFacilityCategory(facilityCategoryQueryDTO);
        long total = facilityCategoryService.count(facilityCategoryQueryDTO);

        Map<String,Object> map = new HashMap<>();
        map.put("list",categories);
        map.put("total",total);
        return Result.success(map);

        //使用pageHelper
//        PageHelper.startPage(currentPage,pageSize);
//        List<User> users = userService.showPageUser(userQueryDTO);
//        return Result.success(new PageInfo<>(users));

    }
    @GetMapping("/tree")
    public Result getTree(){
        List<FacilityCategory> categories = facilityCategoryService.showAllFacilitiesCategories();

        return Result.success(createTree(null,categories)); //pid=null 从第一级开始递归
    }
    //封装一个用完全递归来创建分类树的方法
    private List<FacilityCategory> createTree(Integer pid,List<FacilityCategory> categories){
        List<FacilityCategory> treeList = new ArrayList<>();
        for (FacilityCategory category : categories){
            if (pid == null){
                if (category.getPid() == null){ //第一级节点
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(),categories));
                }
            }else {
                if (pid.equals(category.getPid())){
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(),categories));
                }
            }
            if (CollUtil.isEmpty(category.getChildren())){
                category.setChildren(null);
            }
        }
        return treeList;
    }
    @PostMapping
    public Result add(@RequestBody FacilityCategory facilityCategory){
        if (facilityCategory.getName() == null){
            return Result.error("参数错误");
        }
        try{
            return Result.success(facilityCategoryService.insertFacilityCategory(facilityCategory));
        }catch (Exception e){
            throw new ServiceException("设施已添加");
        }
    }
    @PutMapping
    public Result update(@RequestBody FacilityCategory facilityCategory){
        if (facilityCategory.getName() == null){
            return Result.error("参数错误");
        }
        return Result.success(facilityCategoryService.updateFacilityCategory(facilityCategory));
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (id==null){
            return Result.error("参数错误");
        }
        return Result.success(facilityCategoryService.deleteFacilityCategory(id));
    }
}
