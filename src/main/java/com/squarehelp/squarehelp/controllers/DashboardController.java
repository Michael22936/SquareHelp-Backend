package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.squarehelp.squarehelp.util.Calculator.*;

@Controller
public class DashboardController {
    private final UserRepository userDao;

    public DashboardController(SmokerInfoRepository smokeDao, UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/dashboard")
    public String passingDashboard(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        int totalUsers = (int) userDao.count();
//        System.out.println("totalUsers = " + totalUsers);
        User userInfo = userDao.getOne(id);

        int days = 0;


        // Day counter (int)

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




        // if date_quit_smoking is effected by date_relapsed, then date_relapsed and date_quit_smoking should be identical



















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

        model.addAttribute("users", userInfo);
        model.addAttribute("smoke", userInfo.getSmokerInfo());
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
        System.out.println(userDao.findAll());
        System.out.println("Json of all USERS sent to JS!");
        return userDao.findAll();
    }
}