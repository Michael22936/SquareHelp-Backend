package com.squarehelp.squarehelp.util;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

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

            totalUsersSmokeFreeDays += userTotalSmokeFreeDays;
        }


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
            totalUsersSavings += userTotalSaved;
        }

        double totalCommunityAvgSavings = (double)totalUsersSavings / totalUsers ;

        return String.valueOf(df.format(totalCommunityAvgSavings));

    }

//    Generate total days smoke free

    public static int totalDaysSmokeFree(UserRepository userDao) throws ParseException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        String userQuitDate = userDao.findUserById(id).getSmokerInfo().getDay_quit_smoking();
        String userQuitDateFormatted = parseToJodaTime(userQuitDate);

//        LocalDate userQuiteDateJoda = LocalDate.parse(userQuitDate); // parse to LocalDate Joda time :)

        //        Current Joda time
        DateTime now = new DateTime();

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        DateTime userQuitDateJodaTime = formatter.parseDateTime(userQuitDateFormatted);

//        === Calculate date between date
        Days d = Days.daysBetween( userQuitDateJodaTime, now);
        int days = d.getDays();

        return days;
    }

    public static String parseToJodaTime(String usersTime){

        String year = usersTime.substring(0, 4);
        String day = usersTime.substring(8, 10);
        String month = usersTime.substring(5, 7);
        String newDate = day + "/" + month + "/" + year + " " + "00:00:00";
        return newDate;
    }
}
