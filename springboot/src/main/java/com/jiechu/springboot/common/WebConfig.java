package com.jiechu.springboot.common;

import com.jiechu.springboot.Interceptor.AdminJwtInterceptor;
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

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 指定controller统一的接口前缀
        configurer.addPathPrefix("/api", clazz -> clazz.isAnnotationPresent(RestController.class));
    }
    // 加自定义拦截器JwtInterceptor，设置拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminJwtInterceptor).addPathPatterns("/api/admin/**").excludePathPatterns("/api/admin/login")
                .addPathPatterns("/api/facilityCategory/**")
                .addPathPatterns("/api/facility/**")
                .addPathPatterns("/api/user/page")
                .addPathPatterns("/api/user/list");
    }
}
