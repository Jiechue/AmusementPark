package com.jiechu.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerResponse;
import com.jiechu.springboot.common.Result;
import com.jiechu.springboot.controller.DTO.UserQueryDTO;
import com.jiechu.springboot.entity.User;
import com.jiechu.springboot.exception.ServiceException;
import com.jiechu.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final String BASE_FILE_PATH = System.getProperty("user.dir")+"/files/";
    @PostMapping("/file")
    public Result File(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (StrUtil.isBlank(originalFilename)){
            return Result.error("文件上传");
        }
        long flag = System.currentTimeMillis();
        String filePath = BASE_FILE_PATH + flag + "-" + originalFilename;
        try{
            FileUtil.mkParentDirs(filePath);
            file.transferTo(FileUtil.file(filePath));
            String token = "";
            String url = "http://localhost:9090/user/file/download/"+flag+"?&token="+token;
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
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            return Result.error("参数错误");
        }
        User res = userService.login(user.getUsername(),user.getPassword());
        if(res == null){
            return Result.error("用户名或密码错误");
        }
        return Result.success(res);
    }
    @GetMapping("/list")
    public Result finAllUsers(){
        return Result.success(userService.showAllUser());
    }
    @GetMapping("/page")  // /user/page?currentPage=1&pageSize=20
    public Result finPageUsers(UserQueryDTO userQueryDTO){
        Integer currentPage = userQueryDTO.getCurrentPage();
        Integer pageSize = userQueryDTO.getPageSize();

        if (currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        userQueryDTO.setPageNum(pageNum);

        List<User> users = userService.showPageUser(userQueryDTO);
        long total = userService.count(userQueryDTO);

        Map<String,Object> map = new HashMap<>();
        map.put("list",users);
        map.put("total",total);
        return Result.success(map);

        //使用pageHelper
//        PageHelper.startPage(currentPage,pageSize);
//        List<User> users = userService.showPageUser(userQueryDTO);
//        return Result.success(new PageInfo<>(users));

    }
    @PostMapping
    public Result register(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            return Result.error("参数错误");
        }
        try{
            return Result.success(userService.registerUser(user));
        }catch (DuplicateKeyException e){
            throw new ServiceException("用户名已注册");
        }
    }
    @PutMapping
    public Result update(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            return Result.error("参数错误");
        }
        return Result.success(userService.updateUser(user));
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (id==null){
            return Result.error("参数错误");
        }
        return Result.success(userService.deleteUser(id));
    }
}
