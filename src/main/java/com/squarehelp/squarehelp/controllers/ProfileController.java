package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {

    private final UserRepository userDao;
    private final SmokerInfoRepository smokeDao;

    public ProfileController(UserRepository userDao, SmokerInfoRepository smokeDao){
        this.smokeDao = smokeDao;
        this.userDao = userDao;
    }

    @GetMapping("/profile/{id}")
    public String showProfile(Model model, @PathVariable long id){
        SmokerInfo smokerInfo = smokeDao.getOne(id);
        int moneySaved = smokerInfo.getCost_of_cigs_saved() * smokerInfo.getTotal_days_smoke_free();
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("moneySaved",moneySaved);
        return "profile";
    }

}
