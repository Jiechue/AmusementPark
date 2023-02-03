package com.jiechu.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.jiechu.springboot.common.Result;
import com.jiechu.springboot.entity.Admin;
import com.jiechu.springboot.entity.Facility;
import com.jiechu.springboot.entity.Home;
import com.jiechu.springboot.entity.HomeImage;
import com.jiechu.springboot.service.HomeService;
import com.jiechu.springboot.service.LikeService;
import com.jiechu.springboot.utils.AdminTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeService homeService;
    private static final String BASE_FILE_PATH = System.getProperty("home.dir") + "/files/";
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
            String url = "http://localhost:9090/api/home/file/download/" + flag + "?&token="+token;
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
    @PutMapping
    public Result update(@RequestBody Home home){
        return Result.success(homeService.update(home));
    }
    @GetMapping("/load")
    public Result select(){
        return Result.success(homeService.load());
    }
    @GetMapping("/load-image")
    public Result selectImage(){
        List<String> images = new ArrayList<>();
        for (HomeImage image:homeService.loadImages()){
            images.add(image.getUrl());
        }
        return Result.success(images);
    }
    @PostMapping
    public Result addImg(@RequestBody List<String> urls){
        homeService.deleteImg();
        return Result.success(homeService.addImg(urls));
    }
}
