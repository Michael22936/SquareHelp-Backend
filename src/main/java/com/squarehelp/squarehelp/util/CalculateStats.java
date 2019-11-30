package com.squarehelp.squarehelp.util;

import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.UserRepository;

import java.text.DecimalFormat;
import java.util.List;

public class CalculateStats {


    public static String AvgTotalPointsEarnedAllUsers(UserRepository userDao ){
        List<User> allUsers = userDao.findAll();
        int totalUsersPoints = 0;
        int totalUsers = (int)userDao.count();
//        DecimalFormat df = new DecimalFormat("#.##");  // Format double number

        for (User user : allUsers) {
            String username = user.getUsername();
            int userPoints = user.getSmokerInfo().getPoints();
//            System.out.println("================ userPoints = " + username + " " + userPoints);

//            totalUsersPoints.add
            totalUsersPoints += userPoints;
        }

        int totalCommunityAvgPoints = (totalUsersPoints / totalUsers) ;
//


        return String.valueOf(totalCommunityAvgPoints);
    }

    public static String AvgPointsTotalDaysSmokeFree(UserRepository userDao){
        List<User> allUsers = userDao.findAll();
        int totalUsersSmokeFreeDays = 0;
        int totalUsers = (int)userDao.count();
        DecimalFormat df = new DecimalFormat("#.##");  // Format double number

        for (User user : allUsers) {
            String username = user.getUsername();
            int userTotalSmokeFreeDays = user.getSmokerInfo().getTotal_days_smoke_free();
//            int userPoints = user.getSmokerInfo().getPoints();
//            System.out.println("================ userSmokeFreeDays = " + username + " " + userTotalSmokeFreeDays);

            totalUsersSmokeFreeDays += userTotalSmokeFreeDays;
        }

//            System.out.println("Total Community AVG smoke free days after loop================: " + df.format(totalUsersSmokeFreeDays) );

        double totalCommunityAvgSmokeFreeDays = (double)totalUsersSmokeFreeDays / totalUsers ;

        return String.valueOf(df.format(totalCommunityAvgSmokeFreeDays));

    }

    public static String AvgTotalSavings(UserRepository userDao){
        List<User> allUsers = userDao.findAll();
        int totalUsersSavings = 0;
        int totalUsers = (int)userDao.count();
        DecimalFormat df = new DecimalFormat("#.##");  // Format double number

        for (User user : allUsers) {
            String username = user.getUsername();
            int userTotalSaved = user.getSmokerInfo().getCost_of_cigs_saved();
//            System.out.println("================ userSmokeFreeDays = " + username + " " + userTotalSaved);

            totalUsersSavings += userTotalSaved;
        }

//        System.out.println("Total Community AVG smoke free days after loop================: " + df.format(totalUsersSavings) );

        double totalCommunityAvgSavings = (double)totalUsersSavings / totalUsers ;

        return String.valueOf(df.format(totalCommunityAvgSavings));

    }

}
