package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.joda.time.Days;
import org.joda.time.LocalDate;
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
    private final UserRepository userDao;
    private  final NotificationRepository notiDao;


    public DashboardController(UserRepository userDao, NotificationRepository notiDao) {
        this.userDao = userDao;
        this.notiDao = notiDao;
    }

    @GetMapping("/dashboard")
    public String passingDashboard(Model model) throws ParseException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        int totalUsers = (int) userDao.count();
//        System.out.println("totalUsers = " + totalUsers);
        User userInfo = userDao.getOne(id);



        // Day counter (int)
        int days = 0;

        // if date_relapsed is not null
            // if date_relapsed is after date_quit_smoking, assign 0 days and 0 points



//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
        System.out.println("userInfo.getSmokerInfo().getDay_relapse() = " + userInfo.getSmokerInfo().getDay_relapse());
//        Date relapseDate = sdf.parse(userInfo.getSmokerInfo().getDay_relapse());



        if(userInfo.getSmokerInfo().getDay_relapse() != null) {



//            if (userInfo.getSmokerInfo().getDay_relapse().after( (userInfo.getSmokerInfo().getDay_quit_smoking())) ){
//                days = 0;
//            }
        }


        System.out.println("Relapse day = " + userInfo.getSmokerInfo().getDay_relapse());

        // if date_quit_smoking is effected by date_relapsed, then date_relapsed and date_quit_smoking should be identical





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














        LocalDate start = new LocalDate(userInfo.getDateCreated());
        // if date_quit_smoking is after date_created, start should be date_quit_smoking
        // else start is date_created

        LocalDate end = new LocalDate(LocalDate.now());
//        System.out.println("start = " + start.toString());
//        System.out.println("end.toString() = " + end.toString());



        System.out.println("LocalDate.now().toString() = " + LocalDate.now().toString());
//        if(LocalDate.now().toString().equals("2019-11-26")){
//            System.out.println("\"It's true\" = " + "It's true");
//        }else{
//            System.out.println("wrong");
//        }

        days = Days.daysBetween(start, end).getDays();
        System.out.println("days = " + days);

        // Relapse check (int)
        Date relapseDate = userInfo.getSmokerInfo().getDay_relapse();
        int rCheck = relapseCheck(relapseDate, days);
        if(rCheck == 0){
            days = 0;
        }
        System.out.println("rCheck = " + rCheck);

        // User points  (clean; 5 per diem)
        int userPointsTotal = userPointsCalculator(rCheck);


        // Save to DB
        userInfo.getSmokerInfo().setTotal_days_smoke_free(rCheck);
//        smokeDao.save(userInfo.getSmokerInfo());
//        userDao.save(userInfo.getSmokerInfo());



        // User community stats
        int totalCommunityUsers = avgPointsCalculator(userInfo.getSmokerInfo().getPoints(),totalUsers);

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
        model.addAttribute("user-points", userPointsTotal);
        model.addAttribute("moneySaved", calcMoneySaved(userInfo.getSmokerInfo().getCost_of_cigs_saved(), userInfo.getSmokerInfo().getTotal_days_smoke_free()));
        model.addAttribute("communityCount", totalCommunityUsers);
        return "dashboard";
    }

    @PostMapping("/dashboard/{user_id}")
    public String searchUser(Model model, @RequestParam String searchQuery, @PathVariable long user_id) {
        List<User> searchResults;
        searchResults = userDao.findByUsernameContaining(searchQuery);

        model.addAttribute("ListOfusers", searchResults ) ;
        model.addAttribute("users", userDao.getOne(user_id));

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