package com.squarehelp.squarehelp.controllers;
import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashboardController {
    private final SmokerInfoRepository smokeDao;
    private final UserRepository userDao;

    public DashboardController(SmokerInfoRepository smokeDao, UserRepository userDao) {
        this.smokeDao = smokeDao;
        this.userDao = userDao;
    }

    @GetMapping("/dashboard/{id}")
    public String showDashboard(Model model, @PathVariable long id) {
        SmokerInfo smokerInfo = smokeDao.getOne(id);
        int moneySaved = smokerInfo.getCost_of_cigs_saved() * smokerInfo.getTotal_days_smoke_free();
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("moneySaved", moneySaved);
        return "dashboard";
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
