package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    private final UserRepository userDao;

    public BaseController(UserRepository userDao){
        this.userDao = userDao;
    }

    @GetMapping("/base")
    public String landingPage(Model model){
        model.addAttribute("user", userDao.findAll());
        return "base";
    }

}
