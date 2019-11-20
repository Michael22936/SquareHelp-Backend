package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserRepository userDao;


    public HomeController(UserRepository userDao){
        this.userDao = userDao;
    }

    @GetMapping("/home")
    public String landingPage(Model model){

        model.addAttribute("users", userDao.findAll());

        return "index";
    }

}
