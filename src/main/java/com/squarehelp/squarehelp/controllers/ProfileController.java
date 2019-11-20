package com.squarehelp.squarehelp.controllers;

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
        model.addAttribute("username", userDao.getOne(id));
        return "profile";
    }



}
