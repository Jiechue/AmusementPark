package com.jiechu.springboot.common;

import com.jiechu.springboot.Interceptor.AdminJwtInterceptor;
import com.jiechu.springboot.Interceptor.UserJwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements  WebMvcConfigurer {
    @Autowired
    AdminJwtInterceptor adminJwtInterceptor;
    @Autowired
    UserJwtInterceptor userJwtInterceptor;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 指定controller统一的接口前缀
        configurer.addPathPrefix("/api", clazz -> clazz.isAnnotationPresent(RestController.class));
    }
    // 加自定义拦截器JwtInterceptor，设置拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminJwtInterceptor).addPathPatterns("/api/admin/**").excludePathPatterns("/api/admin/login")
                .addPathPatterns("/api/user/page")
                .addPathPatterns("/api/user/list")
                .addPathPatterns("/api/user/adminUpdate")
                .addPathPatterns("/api/user/adminAdd")
                .addPathPatterns("/api/facilityCategory/**").excludePathPatterns("/api/facilityCategory/tree");
        registry.addInterceptor(userJwtInterceptor).addPathPatterns("/api/action/**")
                .addPathPatterns("/api/user/userUpdate")
                .addPathPatterns("/api/user/findUserById/**")
                .addPathPatterns("/api/message/release");
    }
}
