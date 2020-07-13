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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("smokeinfo", smokeDao.findAll());
        return "community";
    }

}
