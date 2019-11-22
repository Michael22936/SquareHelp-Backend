package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VerificationController {
    private final UserRepository userDao;

    public VerificationController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/verify/message")
    public String getVerifyMessageView(Model model){

        model.addAttribute("users", userDao);

        return "/message";
    }



}
