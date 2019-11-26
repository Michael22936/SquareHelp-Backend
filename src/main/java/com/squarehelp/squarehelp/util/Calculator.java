package com.squarehelp.squarehelp.util;

/*
 * Calculator class is used to compute calculations throughout the site.
 */
public class Calculator {


    public static int calcMoneySaved(int cigCost, int totSmokeFree) {
        return (cigCost * totSmokeFree);
    }

    public static int userDayLapse(){
        // start with date_quit smoking (or sign-up day), add each calendar day
        return 0;
    }

    public static int userPointsCalculator(int dailyPoints, int day){
        // multiply daypoints times lapse
        return 0;
    }


    public static int avgPointsCalculator(int totalPoints, int totalUsers) {
        double d1 = totalPoints;
        double d2 = totalUsers;

        if (d1 != 0 & d2 != 0) {
            int out = (int) Math.round(d1 / d2);

            return out;
        } else {
            return totalPoints;
        }
    }

}