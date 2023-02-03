package com.jiechu.springboot.controller;

import com.jiechu.springboot.common.Result;
import com.jiechu.springboot.controller.DTO.AdminQueryDTO;
import com.jiechu.springboot.controller.DTO.MessageQueryDTO;
import com.jiechu.springboot.controller.DTO.MessageResultByUserDTO;
import com.jiechu.springboot.entity.*;
import com.jiechu.springboot.service.LikeService;
import com.jiechu.springboot.service.MessageService;
import com.jiechu.springboot.service.UserService;
import com.jiechu.springboot.utils.UserTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    LikeService likeService;
    @Autowired
    UserService userService;
    @PostMapping("/release")
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
//    @GetMapping("/list/{id}")
//    public Result findMessagesByFacilityId(@PathVariable Integer id){
//        User user = UserTokenUtils.getCurrentUser();
//        List<Message> messages = messageService.showAllByFacilityId(id);
//        for (Message message : messages){
//            Like like = null;
//            like = likeService.showByUserIdAndMessageId(user.getId(),message.getId());
//            if (like != null){
//                Integer action = like.getAction();
//            }
//        }
//        return Result.success(messageService.showAllByFacilityId(id));
//    }
    @GetMapping("/page")  // /user/page?currentPage=1&pageSize=20
    public Result finPageMessages(MessageQueryDTO messageQueryDTO){
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
    @GetMapping("/page/{id}")  // /user/page?currentPage=1&pageSize=20
    public Result finPageMessagesInFacility(@PathVariable Integer id,MessageQueryDTO messageQueryDTO){
        Integer currentPage = messageQueryDTO.getCurrentPage();
        Integer pageSize = messageQueryDTO.getPageSize();

        if (currentPage == null || currentPage <= 0 || pageSize == null || pageSize < 1){
            return Result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        messageQueryDTO.setPageNum(pageNum);
        messageQueryDTO.setFacilityid(id);

        List<Message> messages = messageService.showPageMessages(messageQueryDTO);

        long total = messageService.count(messageQueryDTO);
        User user = null;
        if (UserTokenUtils.getCurrentUser() !=null){
            user = UserTokenUtils.getCurrentUser();
        }
        List<MessageResultByUserDTO> messageResultByUserDTOList = new ArrayList<>();
        for (Message message : messages){
            System.out.println(message.toString());
            MessageResultByUserDTO messageResultByUserDTO = new MessageResultByUserDTO();
            Like like = null;
            if (user != null){
                like = likeService.showByUserIdAndMessageId(user.getId(),message.getId());
            }

            messageResultByUserDTO.setLike(false);
            messageResultByUserDTO.setDislike(false);
            if (like != null){
                Integer action = like.getAction();

                if (action == 1){
                    messageResultByUserDTO.setLike(true);
                }else if (action == 0){
                    messageResultByUserDTO.setDislike(true);
                }
            }

            User u = userService.showUserById(message.getUserid());
            User messageUser = new User();
            if (u != null){
                messageUser.setAvatar(u.getAvatar());
                messageUser.setUsername(u.getUsername());
                messageUser.setId(u.getId());
            }
            messageResultByUserDTO.setUser(messageUser);
//            messageResultByUserDTO.setId(message.getId());
//            messageResultByUserDTO.setLiketotal(message.getLiketotal());
//            messageResultByUserDTO.setContent(message.getContent());
//            messageResultByUserDTO.setCreatetime(message.getCreatetime());
//            messageResultByUserDTO.setDisliketotal(message.getDisliketotal());
//            messageResultByUserDTO.setFacilityid(message.getFacilityid());

            BeanUtils.copyProperties(message,messageResultByUserDTO);

            messageResultByUserDTO.setUser(messageUser);

            messageResultByUserDTOList.add(messageResultByUserDTO);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("list",messageResultByUserDTOList);
        map.put("total",total);
        return Result.success(map);

        //使用pageHelper
//        PageHelper.startPage(currentPage,pageSize);
//        List<User> users = userService.showPageUser(userQueryDTO);
//        return Result.success(new PageInfo<>(users));

    }
}
