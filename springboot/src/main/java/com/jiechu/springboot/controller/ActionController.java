package com.jiechu.springboot.controller;

import com.jiechu.springboot.common.Result;
import com.jiechu.springboot.controller.DTO.FacilityResultByUserDTO;
import com.jiechu.springboot.controller.DTO.MessageResultByUserDTO;
import com.jiechu.springboot.entity.Facility;
import com.jiechu.springboot.entity.Like;
import com.jiechu.springboot.entity.User;
import com.jiechu.springboot.service.FacilityService;
import com.jiechu.springboot.service.LikeService;
import com.jiechu.springboot.service.MessageService;
import com.jiechu.springboot.utils.UserTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController //是一个组合注解，专门用来开发Restful接口
@RequestMapping("/action")
public class ActionController {
    @Autowired
    LikeService likeService;
    @Autowired
    MessageService messageService;
    @Autowired
    FacilityService facilityService;
    @PostMapping("/like")
    public Result like(@RequestBody MessageResultByUserDTO messageResultByUserDTO){
        Like like = new Like();
        User user = UserTokenUtils.getCurrentUser();
        Integer id = messageResultByUserDTO.getId();
        like.setTargetid(id);
        like.setUserid(user.getId());
        like.setAction(1);

        if (messageResultByUserDTO.isLike()){
            likeService.delete(like);
            messageService.reduceLike(id);
            return Result.success("取消点赞成功");
        }
        if (messageResultByUserDTO.isDislike()){
            likeService.update(like);
            messageService.reduceDisLike(id);
            messageService.addLike(id);
            return Result.success("点赞成功");
        }
        if (!messageResultByUserDTO.isDislike() && !messageResultByUserDTO.isLike()){
            likeService.add(like);
            messageService.addLike(id);
            return Result.success("点赞成功");
        }
        return Result.error("点赞失败");
    }
    @PostMapping("/hate")
    public Result hate(@RequestBody MessageResultByUserDTO messageResultByUserDTO){
        Like like = new Like();
        User user = UserTokenUtils.getCurrentUser();
        Integer id = messageResultByUserDTO.getId();
        like.setTargetid(id);
        like.setUserid(user.getId());
        like.setAction(0);

        if (messageResultByUserDTO.isLike()){
            likeService.update(like);
            messageService.reduceLike(id);
            messageService.addDisLike(id);
            return Result.success("不喜欢成功");
        }
        if (messageResultByUserDTO.isDislike()){
            likeService.delete(like);
            messageService.reduceDisLike(id);
            return Result.success("取消不喜欢成功");
        }
        if (!messageResultByUserDTO.isDislike() && !messageResultByUserDTO.isLike()){
            likeService.add(like);
            messageService.addDisLike(id);
            return Result.success("不喜欢成功");
        }
        return Result.error("不喜欢失败");
    }
    @PostMapping("/likeFacility")
    public Result likeFacility(@RequestBody FacilityResultByUserDTO facilityResultByUserDTO){

        Like like = new Like();
        User user = UserTokenUtils.getCurrentUser();
        Integer id = facilityResultByUserDTO.getId();
        like.setTargetid(id);
        like.setUserid(user.getId());

        if (facilityResultByUserDTO.isLike()){
            facilityService.reduceLikeTotal(id);
            likeService.deleteFacility(like);
            return Result.success("取消点赞成功");
        }else {
            facilityService.addLikeTotal(id);
            likeService.addFacility(like);
            return Result.success("点赞成功");
        }
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (id==null){
            return Result.error("参数错误");
        }
        return Result.success();
    }
}
