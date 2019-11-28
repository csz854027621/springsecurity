package com.csz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {


   /* public static void main(String[] args) {
        String s = Format.dataToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(s);
    }*/

    public static String dataToString(Date date,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        String newStr = sdf.format(date);
        return newStr;
    }

    public static Date ToDate(String str,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        Date newDate = null;
        try {
            newDate = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

}
