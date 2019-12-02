package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.squarehelp.squarehelp.util.CalculateStats.*;
import static com.squarehelp.squarehelp.util.Calculator.*;
import static com.squarehelp.squarehelp.util.UnreadNotifications.unreadNotificationsCount;

@Controller
public class DashboardController {
    private final SmokerInfoRepository smokeDao;
    private final UserRepository userDao;
    private  final NotificationRepository notiDao;

    public DashboardController(SmokerInfoRepository smokeDao, UserRepository userDao, NotificationRepository notiDao) {
        this.smokeDao = smokeDao;
        this.userDao = userDao;
        this.notiDao = notiDao;
    }

    @GetMapping("/dashboard")
    public String passingDashboard(Model model) throws ParseException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        int totalUsers = (int) userDao.count();
        System.out.println("totalUsers = " + totalUsers);
//        System.out.println("totalPoints = " + totalPoints);
        SmokerInfo smokerInfo = smokeDao.getOne(id);

        // Get lapse of days (from day quit smoking to current date)
        DateTime start = new DateTime(smokerInfo.getDay_quit_smoking());
        DateTime end = new DateTime(DateTime.now());
        int days = Days.daysBetween(start, end).getDays();
        System.out.println("days = " + days);
        System.out.println("==================== smokerInfo.getDay_quit_smoking() = " + smokerInfo.getDay_quit_smoking());
        System.out.println("===================== start = " + start);

        // Get relapse day (if needed)
        Date relapseDate = smokerInfo.getDay_relapse();
//        DateTime rStart = new DateTime(relapseDate);
//        int resetDays = Days.daysBetween(rStart, end).getDays();

        int rCheck = relapseCheck(relapseDate, days);
        System.out.println("days now = " + days);


        // Get points for user (5 points per day)
        int userPointsTotal = userPointsCalculator(rCheck);

        int totalCommunityUsers = avgPointsCalculator(smokerInfo.getPoints(),totalUsers);

        //========= Gets the count of unread notifications
        int unreadNotifications = unreadNotificationsCount(notiDao, id);

//        ==== Returns Total Avg points earned by all users. ====
        String avgTotalPoints = AvgTotalPointsEarnedAllUsers(userDao);
//        ==== Returns Avg Days smoke free for all users.
        String avgTotalSmokeFreeDays = AvgPointsTotalDaysSmokeFree(userDao);
//        ==== Returns Avg Days smoke free for all users.
        String avgTotalCigsSavings = AvgTotalSavings(userDao);

//        ====== Generate total days smoke free
        totalDaysSmokeFree(userDao);

        model.addAttribute("avgTotalSavings", avgTotalCigsSavings);
        model.addAttribute("avgTotalSmokeFreeDays", avgTotalSmokeFreeDays);
        model.addAttribute("avgTotalUsersPoints", avgTotalPoints );
        model.addAttribute("alertCount", unreadNotifications); // shows count for unread notifications
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
        return userDao.findAll();
    }

    @GetMapping("/searchUser")
    @ResponseBody
    public List<User> sendSignedInUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        List<User> ListSignedInUser = new ArrayList<>();
        ListSignedInUser.add(userDao.findUserById(id));
        return ListSignedInUser;
    }
}