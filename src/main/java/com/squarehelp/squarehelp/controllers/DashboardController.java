package com.squarehelp.squarehelp.controllers;

import com.google.gson.Gson;
import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class DashboardController {
    private final SmokerInfoRepository smokeDao;
    private final UserRepository userDao;

    public DashboardController(SmokerInfoRepository smokeDao, UserRepository userDao){
        this.smokeDao = smokeDao;
        this.userDao = userDao;
    }


}
//    private String convertPersonalStatsJson(SmokerInfo s, User u, String total) {
//        // Create output collection
//        Map<String, String> temp = new HashMap<>();
//
//        // Add values to temp object
//        temp.put("costOfCigsSaved", String.valueOf(s.getCost_of_cigs_saved()));
//        temp.put("totalDaysSmokeFree", String.valueOf(s.getTotal_days_smoke_free()));
//        temp.put("points", String.valueOf(s.getPoints()));
//        temp.put("totalSaved", total);
//
//        // Create new Gson object and add contents and convert to Json
//        Gson g = new Gson();
//        String out = g.toJson(temp);
//
//        return out;
//    }
