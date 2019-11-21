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
    //    private String user_id /// @Column annotation generated
    private int points;
    private int total_days_smoke_free;
    private int cost_of_cigs_saved;


    public static int calcMoneySaved(int cigCost, int totSmokeFree) {
        return cigCost * totSmokeFree;
    }


    public static int avgPointsCalculator(int points, int totalUsers) {
        return totalUsers / points;
    }








//    @GetMapping("/dashboard/{user_id}")
//    public String showDashboard(Model model, @PathVariable long user_id) {
//        SmokerInfo smokerInfo = smokeDao.getOne(user_id);
//
//        // Calculate money saved
////        long moneySaved = smokerInfo.getCost_of_cigs_saved() * smokerInfo.getTotal_days_smoke_free();
//
//        model.addAttribute("users", userDao.getOne(user_id));
//        model.addAttribute("smoke", smokerInfo);
////        model.addAttribute("moneySaved", moneySaved);
//        return "dashboard";
//    }
}
