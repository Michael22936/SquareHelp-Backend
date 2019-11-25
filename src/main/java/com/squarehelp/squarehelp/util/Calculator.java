package com.squarehelp.squarehelp.util;

/*
 * Calculator class is used to compute calculations throughout the site.
 */
public class Calculator {

<<<<<<< HEAD
    private long id;
    private int points;
    private int total_days_smoke_free;
    private int cost_of_cigs_saved;


// ====  Methods are intended for the dashboard and profile controllers  ==== //
=======
>>>>>>> c31c27e1146cc2f90c95187f1330935a7197dc93
    public static int calcMoneySaved(int cigCost, int totSmokeFree) {
        return (cigCost * totSmokeFree);
    }

    public static int avgPointsCalculator(int points, int totalUsers) {
<<<<<<< HEAD
        if(points == 0){
            return 0;
        }
        return (totalUsers / points);
    }


    public static void main(String[] args) {
        System.out.println("avgPointsCalculator(45, 4) = " + avgPointsCalculator(45, 4));
        System.out.println("calcMoneySaved(45, 4) = " + calcMoneySaved(45, 4));
    }

=======
        if (points != 0 & totalUsers != 0) {
            return totalUsers / points;
        } else {
            return points;
        }
    }
>>>>>>> c31c27e1146cc2f90c95187f1330935a7197dc93
}
