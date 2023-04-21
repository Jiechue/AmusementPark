package com.jiechu.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.jiechu.springboot.common.Result;
import com.jiechu.springboot.common.SecurePassword;
import com.jiechu.springboot.controller.DTO.AdminLoginDTO;
import com.jiechu.springboot.controller.DTO.UpdatePasswordDTO;
import com.jiechu.springboot.controller.DTO.UserLoginDTO;
import com.jiechu.springboot.controller.DTO.UserQueryDTO;
import com.jiechu.springboot.entity.Admin;
import com.jiechu.springboot.entity.User;
import com.jiechu.springboot.exception.ServiceException;
import com.jiechu.springboot.service.UserService;
import com.jiechu.springboot.utils.AdminTokenUtils;
import com.jiechu.springboot.utils.UserTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "/files/";
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
            System.out.println(currentAdmin.toString());
            String token = AdminTokenUtils.getToken(currentAdmin.getId().toString(),currentAdmin.getPassword());
            String url = "http://localhost:9090/api/user/file/download/" + flag + "?&token="+token;
            if (originalFilename.endsWith("png") || originalFilename.endsWith("jpg") || originalFilename.endsWith("pdf")){
                url += "&play=1";
            }
            return Result.success(url);
        }catch (Exception e){
            log.info("文件上传失败",e);
        }
        return Result.error("文件上传失败");
    }
    @PostMapping("/file/userUpload")
    public Result UserFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (StrUtil.isBlank(originalFilename)){
            return Result.error("文件上传失败");
        }
        long flag = System.currentTimeMillis();
        String filePath = BASE_FILE_PATH + flag + "_" + originalFilename;
        try{
            FileUtil.mkParentDirs(filePath);
            file.transferTo(FileUtil.file(filePath));
            User currentAdmin = UserTokenUtils.getCurrentUser();
            System.out.println(currentAdmin.toString());
            String token = UserTokenUtils.getToken(currentAdmin.getId().toString(),currentAdmin.getPassword());
            String url = "http://localhost:9090/api/user/file/download/" + flag + "?&token="+token;
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
        UserLoginDTO res = userService.login(user.getUsername(), user.getPassword());
        if(res == null){
            return Result.error("用户名或密码错误");
        }
        return Result.success(res);
    }
    @PostMapping("/findUserById")
    public Result findUser(@RequestBody UserLoginDTO userLoginDTO){
        User user = userService.showUserById(userLoginDTO.getId());
        userLoginDTO.setAvatar(user.getAvatar());
        return Result.success(userLoginDTO);
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
    @PostMapping("/adminAdd")
    public Result adminRegister(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            return Result.error("参数错误");
        }
        try{
            return Result.success(userService.registerUser(user));
        }catch (DuplicateKeyException e){
            throw new ServiceException("用户名已注册");
        }
    }
    @PostMapping("/userAdd")
    public Result userRegister(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            return Result.error("参数错误");
        }
        try{
            return Result.success(userService.registerUser(user));
        }catch (DuplicateKeyException e){
            throw new ServiceException("用户名已注册");
        }
    }
    @PutMapping("/adminUpdate")
    public Result adminUpdate(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            return Result.error("参数错误");
        }
        return Result.success(userService.updateUser(user));
    }
    @PutMapping("/userUpdate")
    public Result userUpdate(@RequestBody User user){
        if (user.getUsername() == null||user.getPassword()==null){
            return Result.error("参数错误");
        }
        return Result.success(userService.updateUser(user));
    }
    @PutMapping("/reset")
    public Result Reset(@RequestBody User user){
        Random random = new Random();
        String newPassword = String.valueOf(random.nextInt(10000));
        String password = newPassword;

        user.setPassword(SecurePassword.securePassword(newPassword));
        if (userService.resetPassword(user)){
            return Result.success(password);
        }
        return Result.error("重置错误");
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (id==null){
            return Result.error("参数错误");
        }
        return Result.success(userService.deleteUser(id));
    }
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody UpdatePasswordDTO userUpdatePasswordDTO){
        System.out.println(userUpdatePasswordDTO);
        if (userService.showUserById(userUpdatePasswordDTO.getId()).getPassword().equals(SecurePassword.securePassword(userUpdatePasswordDTO.getOldPassword()))){
            return Result.success(userService.updateUserPassword(userUpdatePasswordDTO));
        }else {
            return Result.error("输入的旧密码与原密码不一致");
        }
    }
}
