package com.squarehelp.squarehelp.util;

/*
 * Calculator class is used to compute calculations throughout the site.
 */
public class Calculator {


    public static int calcMoneySaved(int cigCost, int totSmokeFree) {
        return (cigCost * totSmokeFree);
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


    public static void main(String[] args) {
        System.out.println("calcMoneySaved(45, 4) = " + calcMoneySaved(45, 4));
        System.out.println("avgPointsCalculator(45, 4) = " + avgPointsCalculator(45, 4));


    }
}




//        if(points == 0){
//                return 0;
//                }
//                return (totalUsers / points);
//                }