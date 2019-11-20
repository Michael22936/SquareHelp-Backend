package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegisterController {

    private final UserRepository userDao;
    private final SmokerInfoRepository smokerDao;

    public RegisterController(UserRepository userDao, SmokerInfoRepository smokerDao) {
        this.userDao = userDao;
        this.smokerDao = smokerDao;
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
        public String RegisterNewUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
        System.out.println("username = " + user.getUsername());
        System.out.println("user.getEmail() = " + user.getEmail());
        return "redirect:/login";


    }

}
