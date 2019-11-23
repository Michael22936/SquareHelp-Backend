package com.squarehelp.squarehelp.util;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Calculator {

    private long id;
    private int points;
    private int total_days_smoke_free;
    private int cost_of_cigs_saved;


// ====  Methods are intended for the dashboard and profile controllers  ==== //
    public static int calcMoneySaved(int cigCost, int totSmokeFree) {
        return (cigCost * totSmokeFree);
    }


    public static int avgPointsCalculator(int points, int totalUsers) {
        if(points == 0){
            return 0;
        }
        return (totalUsers / points);
    }


    public static void main(String[] args) {
        System.out.println("avgPointsCalculator(45, 4) = " + avgPointsCalculator(45, 4));
        System.out.println("calcMoneySaved(45, 4) = " + calcMoneySaved(45, 4));
    }

}
