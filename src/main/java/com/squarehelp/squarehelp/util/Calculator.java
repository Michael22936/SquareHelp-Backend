package com.squarehelp.squarehelp.util;

/*
 * Calculator class is used to compute calculations throughout the site.
 */
public class Calculator {

    public static int calcMoneySaved(int cigCost, int totSmokeFree) {
        return cigCost * totSmokeFree;
    }

    public static int avgPointsCalculator(int points, int totalUsers) {
        if (points != 0 & totalUsers != 0) {
            return totalUsers / points;
        } else {
            return points;
        }
    }
}
