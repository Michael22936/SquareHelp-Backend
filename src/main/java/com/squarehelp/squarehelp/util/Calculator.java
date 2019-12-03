package com.squarehelp.squarehelp.util;

import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.models.Verification;
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


//    public static int userPointsCalculator(int day, int uPoints){
//        int dailyPoints = 1;
//
//        if(day != 0){
//            int out = dailyPoints * day;
//
//            return out;
//        }else {
//           int roundPoints = Math.round((uPoints / 2));
//
//            return roundPoints;
//        }
//    }


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

    public static void veriApproval(int originatorUserId ,Verification veriUser ,Boolean isChangesUpdated, Boolean isApproved, Boolean isPending, UserRepository userDao, Long id, String veriDateCreated, String senderUsername){
        int dailyPoints = 1;
        Long userId = (long)originatorUserId;

        //     if the request is not pending (false) and was not approved, run this...
        if( isApproved == false && isPending == false && isChangesUpdated == false ){
            User user = userDao.findUserById(userId);
            String oldUsersSmokeFreeDay2 = user.getSmokerInfo().getDay_quit_smoking();
            int currentUserPoints = user.getSmokerInfo().getPoints();
        int roundPoints = Math.round((currentUserPoints / 2));
            user.getSmokerInfo().setDay_quit_smoking(veriDateCreated);
            user.getSmokerInfo().setPoints(roundPoints);
            veriUser.setIs_changes_updated(true);

            System.out.println("=========== Username losing points " + user.getUsername());
            System.out.println("===========User Original Day they quit smoking: " + oldUsersSmokeFreeDay2 );
            System.out.println("===========User Day quit smoking set to: " + veriDateCreated );
            System.out.println("===========User Original points : " + currentUserPoints );
            System.out.println("===========User loses (roundPoints): " + roundPoints );
            System.out.println("===========Verifications is Changes updated: " + veriUser.getIs_changes_updated() );

            userDao.save(user);
        }
        if ( isApproved == true && isPending == false && isChangesUpdated == false){
            User user = userDao.findUserById(userId);
            int userCurrentPoints = user.getSmokerInfo().getPoints();
            int usersPointsPlusOne = (userCurrentPoints + dailyPoints);
            int userCurrentSFDays = user.getSmokerInfo().getTotal_days_smoke_free();
            int SFDaysPlusOne = (userCurrentSFDays + dailyPoints);

            veriUser.setIs_changes_updated(true);
            user.getSmokerInfo().setPoints(usersPointsPlusOne);
            user.getSmokerInfo().setTotal_days_smoke_free(SFDaysPlusOne);
            System.out.println("=========== Current Smoke free days = " + userCurrentSFDays);
            System.out.println("=========== Current smoke free days plus one = " + SFDaysPlusOne);




            System.out.println("=========== Username gaining 1 point " + user.getUsername());
            System.out.println("=========== Users current points: " + userCurrentPoints);
            System.out.println("===========User WINS 1 points: " + usersPointsPlusOne );
            System.out.println("===========Verifications is Changes updated: " + veriUser.getIs_changes_updated() );
            userDao.save(user);
        }
    }

}