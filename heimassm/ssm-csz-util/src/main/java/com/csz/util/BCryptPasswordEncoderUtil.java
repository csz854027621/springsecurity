package com.csz.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtil {

    private static BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public static  String getPasswordEncoderString(String str){
     return  passwordEncoder.encode(str);
    }

    public static void main(String[] args) {
        String csz = BCryptPasswordEncoderUtil.getPasswordEncoderString("csz");
        System.out.println(csz);
    }


}
