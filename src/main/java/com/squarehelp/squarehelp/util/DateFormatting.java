package com.squarehelp.squarehelp.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormatting {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat(
                "MM-dd-yyyy");
//        int year = 2014;
//        int month = 10;
//        int day = 31;
        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, year);
//        cal.set(Calendar.MONTH, month - 1);
//        cal.set(Calendar.DAY_OF_MONTH, day);

        java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
        System.out.println(sdf.format(date));



    }



}
