package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
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
        model.addAttribute("smokeInfo", new SmokerInfo());
        return "register";
    }

    @PostMapping("/register")
        public String RegisterNewUser(@ModelAttribute User user, @RequestParam String costOfCigs, @RequestParam String dateQuitSmoking, @RequestParam String dob){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
        System.out.println("username = " + user.getUsername());
        System.out.println("user email = " + user.getEmail());
        System.out.println("user DOB = " + dob);
        System.out.println("user phoneNumber = " + user.getPhoneNumber());
        System.out.println("user Gender = " + user.getGender());
        System.out.println("user day_quit_smoking = " + dateQuitSmoking);
        System.out.println("user cost_of_cigs_saved = " + costOfCigs);

        return "redirect:/login";


    }

}
