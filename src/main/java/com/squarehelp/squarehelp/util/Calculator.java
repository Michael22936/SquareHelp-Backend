package com.squarehelp.squarehelp.util;

/*
 * Calculator class is used to compute calculations throughout the site.
 */
public class Calculator {


    public static int calcMoneySaved(int cigCost, int totSmokeFree) {
        return (cigCost * totSmokeFree);
    }

    public static int avgPointsCalculator(int points, int totalUsers) {
    /// method needs to convert int params into float numbers for accurate averages

        if (points != 0 & totalUsers != 0) {
            return totalUsers / points;
        } else {
            return points;
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