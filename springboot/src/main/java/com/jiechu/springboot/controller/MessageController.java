package com.jiechu.springboot.controller;

import com.jiechu.springboot.common.Result;
import com.jiechu.springboot.controller.DTO.AdminQueryDTO;
import com.jiechu.springboot.controller.DTO.MessageQueryDTO;
import com.jiechu.springboot.entity.Admin;
import com.jiechu.springboot.entity.Facility;
import com.jiechu.springboot.entity.Like;
import com.jiechu.springboot.entity.Message;
import com.jiechu.springboot.service.LikeService;
import com.jiechu.springboot.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    LikeService likeService;
    @PostMapping
    public Result add(@RequestBody Message message){
//        if (message.getName() == null){
//            return Result.error("参数错误");
//        }
        return Result.success(messageService.add(message));
    }
    @GetMapping("/list")
    public Result finAllMessages(){
        return Result.success(messageService.showAll());
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (id==null){
            return Result.error("参数错误");
        }
        return Result.success(messageService.delete(id));
    }
    @GetMapping("/list/{id}")
    public Result findMessagesByFacilityId(@PathVariable Integer id){
        Integer userid = 0;
        List<Message> messages = messageService.showAllByFacilityId(id);
        for (Message message : messages){
            message.getId();
            Like like = likeService.showByUserIdAndMessageId()
        }
        return Result.success(messageService.showAllByFacilityId(id));
    }
    @GetMapping("/page")  // /user/page?currentPage=1&pageSize=20
    public Result finPageAdmins(MessageQueryDTO messageQueryDTO){
        Integer currentPage = messageQueryDTO.getCurrentPage();
        Integer pageSize = messageQueryDTO.getPageSize();

        if (currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        messageQueryDTO.setPageNum(pageNum);

        List<Message> messages = messageService.showPageMessages(messageQueryDTO);
        long total = messageService.count(messageQueryDTO);

        Map<String,Object> map = new HashMap<>();
        map.put("list",messages);
        map.put("total",total);
        return Result.success(map);

        //使用pageHelper
//        PageHelper.startPage(currentPage,pageSize);
//        List<User> users = userService.showPageUser(userQueryDTO);
//        return Result.success(new PageInfo<>(users));

    }
}
