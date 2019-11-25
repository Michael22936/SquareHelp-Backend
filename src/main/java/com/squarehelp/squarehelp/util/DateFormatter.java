package com.squarehelp.squarehelp.util;

public class DateFormatter {

    /*
     * Method converts the database date to: mm-dd-yyyy
     */
    public static String convertDate(String d) {
        String[] fmtDate = d.split("-");
        String out = fmtDate[1] + "-" + fmtDate[2] + "-" + fmtDate[0];
        return out;
    }

}
