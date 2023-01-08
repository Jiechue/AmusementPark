package com.jiechu.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.jiechu.springboot.common.Result;
import com.jiechu.springboot.controller.DTO.FacilityCategoryQueryDTO;
import com.jiechu.springboot.controller.DTO.FacilityQueryDTO;
import com.jiechu.springboot.entity.Admin;
import com.jiechu.springboot.entity.Facility;
import com.jiechu.springboot.entity.FacilityCategory;
import com.jiechu.springboot.exception.ServiceException;
import com.jiechu.springboot.service.FacilityService;
import com.jiechu.springboot.utils.AdminTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/facility")
public class FacilityController {
    @Autowired
    FacilityService facilityService;
    private static final String BASE_FILE_PATH = System.getProperty("facility.dir") + "/files/";
    @PostMapping("/file/upload")
    public Result File(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (StrUtil.isBlank(originalFilename)){
            return Result.error("文件上传失败");
        }
        long flag = System.currentTimeMillis();
        String filePath = BASE_FILE_PATH + flag + "_" + originalFilename;
        try{
            FileUtil.mkParentDirs(filePath);
            file.transferTo(FileUtil.file(filePath));
            Admin currentAdmin = AdminTokenUtils.getCurrentAdmin();
            String token = AdminTokenUtils.getToken(currentAdmin.getId().toString(),currentAdmin.getPassword());
            String url = "http://localhost:9090/api/facility/file/download/" + flag + "?&token="+token;
            if (originalFilename.endsWith("png") || originalFilename.endsWith("jpg") || originalFilename.endsWith("pdf")){
                url += "&play=1";
            }
            return Result.success(url);
        }catch (Exception e){
            log.info("文件上传失败",e);
        }
        return Result.error("文件上传失败");
    }
    @GetMapping("/file/download/{flag}")
    public void download(@PathVariable String flag, @RequestParam(required = false) String play, HttpServletResponse response){
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(BASE_FILE_PATH);
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try{
            if (StrUtil.isNotEmpty(fileName)){
                String realName = fileName.substring(fileName.indexOf("_")+1);
                if ("1".equals(play)){
                    response.addHeader("Content-Disposition","inline;filename="+ URLEncoder.encode(realName,"UTF-8"));
                }else {
                    response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(realName,"UTF-8"));
                }
                byte[] bytes = FileUtil.readBytes(BASE_FILE_PATH + fileName);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e){
            log.error("文件下载失败",e);
        }
    }
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
    @GetMapping
    public Result show(){
        return Result.success(facilityService.showAllFacilities());
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
