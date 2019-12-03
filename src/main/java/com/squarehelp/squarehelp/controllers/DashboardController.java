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
        User signedInUser = userDao.getOne(id);
        int totalUsers = (int) userDao.count();

        String userQuitSmokeFreeDay = signedInUser.getSmokerInfo().getDay_quit_smoking();

        System.out.println("User day quit smoking = " + userQuitSmokeFreeDay);

        // Initial lapse of days (zero smoke days)
//        DateTime userDayQuitSmoking = user.getSmokerInfo().getDay_quit_smoking();
        DateTime start = new DateTime( userQuitSmokeFreeDay );
        DateTime end = new DateTime(DateTime.now());
        int days = Days.daysBetween(start, end).getDays();

        // Updates users Smoke info
        User userSave = userDao.getOne(id);
        userSave.getSmokerInfo().setTotal_days_smoke_free(days);
        userDao.save( userSave );

//        System.out.println("===================== Math = " + user.getSmokerInfo().getPoints() / 2);

        // Get relapse day (if needed)
        Date relapseDate = signedInUser.getSmokerInfo().getDay_relapse();
//        DateTime rStart = new DateTime(relapseDate);
//        int resetDays = Days.daysBetween(rStart, end).getDays();

        int rCheck = relapseCheck(relapseDate, days);
        System.out.println("days now = " + days);


        // Get points for user (5 points per day)
        int userPointsTotal = userPointsCalculator(days, signedInUser.getSmokerInfo().getPoints());
        signedInUser.getSmokerInfo().setPoints(userPointsTotal);
        userDao.save(signedInUser);

        int totalCommunityUsers = avgPointsCalculator(signedInUser.getSmokerInfo().getPoints(),totalUsers);

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
        model.addAttribute("smoke", signedInUser.getSmokerInfo());
        model.addAttribute("user-points", userPointsTotal);
        model.addAttribute("moneySaved", calcMoneySaved(signedInUser.getSmokerInfo().getCost_of_cigs_saved(), signedInUser.getSmokerInfo().getTotal_days_smoke_free()));
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