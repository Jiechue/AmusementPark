package com.jiechu.springboot.common;

import cn.hutool.crypto.SecureUtil;

public class SecurePassword {
    private static final String PASS_SALT = "Password";
    public static String securePassword(String password){
        return SecureUtil.md5(password + PASS_SALT);
    }
}
