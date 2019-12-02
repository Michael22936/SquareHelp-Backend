package com.squarehelp.squarehelp.util;

import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.UserRepository;

import java.util.Date;

/*
 * Calculator class is used to compute calculations throughout the site.
 */
public class Calculator {


    public static int calcMoneySaved(int cigCost, int totSmokeFree) {
        return (cigCost * totSmokeFree);
    }

    public static int relapseCheck(Date relapseDate, int days){
        if(relapseDate != null){
            int reset = 0;
            return reset;
        }else {
            return days;
        }
    }


    public static int userPointsCalculator(int day){
        int dailyPoints = 5;

        if(day != 0){
            int out = dailyPoints * day;

            return out;
        }else {
            // resets user points to zero
            return 0;
        }
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

    public static void veriApproval(Boolean veriApprove, UserRepository userDao, Long id,){
        if(veriApprove == false){
            User user = userDao.findUserById(id);
            user.getSmokerInfo().setDay_quit_smoking();
        }
    }

}