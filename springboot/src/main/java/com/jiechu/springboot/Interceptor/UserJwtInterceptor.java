package com.jiechu.springboot.Interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jiechu.springboot.entity.User;
import com.jiechu.springboot.exception.ServiceException;
import com.jiechu.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class UserJwtInterceptor implements HandlerInterceptor {
    private static final String ERROR_CODE_402 = "402";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userToken = request.getHeader("userToken");
        if (StrUtil.isBlank(userToken)) {
            userToken = request.getParameter("userToken");
        }

        // 执行认证
        if (StrUtil.isBlank(userToken)) {
            throw new ServiceException(ERROR_CODE_402, "无userToken，请重新登录");
        }
        // 获取 token 中的adminId
        String userId;
        User user;
        try {
            userId = JWT.decode(userToken).getAudience().get(0);
            // 根据token中的userid查询数据库
            user = userService.showUserById(Integer.parseInt(userId));
        } catch (Exception e) {
            String errMsg = "userToken验证失败，请重新登录";
            log.error(errMsg + ", token=" + userToken, e);
            throw new ServiceException(ERROR_CODE_402, errMsg);
        }
        if (user == null) {
            throw new ServiceException(ERROR_CODE_402, "用户不存在，请重新登录");
        }

        try {
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(userToken); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException(ERROR_CODE_402, "userToken验证失败，请重新登录");
        }
        return true;
    }
}
