package com.squarehelp.squarehelp.controllers;
import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.squarehelp.squarehelp.util.Calculator.avgPointsCalculator;
import static com.squarehelp.squarehelp.util.Calculator.calcMoneySaved;


@Controller
public class DashboardController {
    private final SmokerInfoRepository smokeDao;
    private final UserRepository userDao;

    public DashboardController(SmokerInfoRepository smokeDao, UserRepository userDao) {
        this.smokeDao = smokeDao;
        this.userDao = userDao;
    }

    @GetMapping("/dashboard/{user_id}")
    public String showDashboard(Model model, @PathVariable long user_id) {
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);

        // Calculate money saved
//        long moneySaved = smokerInfo.getCost_of_cigs_saved() * smokerInfo.getTotal_days_smoke_free();

        int totalUsers = (int)userDao.count();
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());
        int totalCommunityUsers = avgPointsCalculator(smokerInfo.getPoints(),totalUsers);

        model.addAttribute("users", userDao.getOne(user_id));
        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("moneySaved", moneySaved);
//        System.out.println("smokerInfo.getCost_of_cigs_saved() = " + smokerInfo.getCost_of_cigs_saved());
//        System.out.println("smokerInfo.getTotal_days_smoke_free() = " + smokerInfo.getTotal_days_smoke_free());
//        System.out.println("moneySaved = " + moneySaved);
        model.addAttribute("communityCount", totalCommunityUsers);
//        System.out.println("totalUsers = " + totalUsers);
//        System.out.println("smokerInfo.getCost_of_cigs_saved() = " + smokerInfo.getCost_of_cigs_saved());
        System.out.println("totalCommunityUsers = " + totalCommunityUsers);
        return "dashboard";
    }
}