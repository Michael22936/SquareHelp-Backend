package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class CommunityController {
    private final UserRepository userDao;
    private final SmokerInfoRepository smokeDao;

    public CommunityController(UserRepository userDao, SmokerInfoRepository smokeDao){
        this.smokeDao = smokeDao;
        this.userDao = userDao;
    }

    @GetMapping("/community")
    public String CommunityPage(Model model){

        return "community";
    }

}
