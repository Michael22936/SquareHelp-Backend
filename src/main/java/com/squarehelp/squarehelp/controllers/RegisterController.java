//package com.squarehelp.squarehelp.controllers;
//
//import com.squarehelp.squarehelp.models.SmokerInfo;
//import com.squarehelp.squarehelp.models.User;
//import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
//import com.squarehelp.squarehelp.repositories.UserRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.Date;
//
//
//@Controller
//public class RegisterController {
//
//    private final UserRepository userDao;
//    private final SmokerInfoRepository smokerDao;
//
//    public RegisterController(UserRepository userDao, SmokerInfoRepository smokerDao) {
//        this.userDao = userDao;
//        this.smokerDao = smokerDao;
//    }
//
//    @GetMapping("/register")
//    public String registerPage(Model model){
//        model.addAttribute("user", new User());
//        model.addAttribute("smokeInfo", new SmokerInfo());
//
//        return "register";
//    }
//

//
//}
