package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.squarehelp.squarehelp.util.Calculator.*;

@Controller
public class DashboardController {
    private final SmokerInfoRepository smokeDao;
    private final UserRepository userDao;

    public DashboardController(SmokerInfoRepository smokeDao, UserRepository userDao) {
        this.smokeDao = smokeDao;
        this.userDao = userDao;
    }

    @GetMapping("/dashboard")
    public String passingDashboard(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        int totalUsers = (int) userDao.count();
        System.out.println("totalUsers = " + totalUsers);
        SmokerInfo smokerInfo = smokeDao.getOne(id);

        // Get lapse of days (from day quit smoking to current date)
        DateTime start = new DateTime(smokerInfo.getDay_quit_smoking());
        DateTime end = new DateTime(DateTime.now());
        int days = Days.daysBetween(start, end).getDays();
        System.out.println("days = " + days);

        // Relapse check
        Date relapseDate = smokerInfo.getDay_relapse();
        int rCheck = relapseCheck(relapseDate, days);
        System.out.println("days now = " + days);


        // Get points for user (5 points per day)
        int userPointsTotal = userPointsCalculator(rCheck);


        // Save to DB
        smokerInfo.setTotal_days_smoke_free(days);
        smokeDao.save(smokerInfo);


        // User community stats
        int totalCommunityUsers = avgPointsCalculator(smokerInfo.getPoints(),totalUsers);

        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("user-points", userPointsTotal);
        model.addAttribute("moneySaved", calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free()));
        model.addAttribute("communityCount", totalCommunityUsers);
        return "dashboard";
    }

    @PostMapping("/dashboard/{user_id}")
    public String searchUser(Model model, @RequestParam String searchQuery, @PathVariable long user_id) {
        List<User> searchResults;
        searchResults = userDao.findByUsernameContaining(searchQuery);

        model.addAttribute("ListOfusers", searchResults ) ;
        model.addAttribute("users", userDao.getOne(user_id));
        model.addAttribute("smoke", smokeDao.getOne(user_id));

        return "dashboard";
    }


    @GetMapping("/searchAll")
    @ResponseBody
    public List<User> sendAllUsers(){
//        System.out.println(userDao.findByUsernameContaining(username));
        System.out.println(userDao.findAll());
        System.out.println("Json of all USERS sent to JS!");
        return userDao.findAll();
    }
}