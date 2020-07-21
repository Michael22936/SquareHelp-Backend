package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommunityController {

    private final SmokerInfoRepository smokeDao;
    private final UserRepository userDao;

    public CommunityController(UserRepository userDao, SmokerInfoRepository smokeDao){
        this.userDao = userDao;
        this.smokeDao = smokeDao;
    }

    @GetMapping("/community")
    public String CommunityPage(Model model){

        model.addAttribute("users", userDao.findAll());

        return "community";
    }

}
