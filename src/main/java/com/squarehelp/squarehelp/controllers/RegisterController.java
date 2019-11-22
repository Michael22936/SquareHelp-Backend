package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


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
    public String RegisterNewUser(@ModelAttribute User user, @ModelAttribute SmokerInfo smokerInfo ) throws ParseException {
        // Pulls last user id and adds 1 to create user_id for smoking_into.
        int newUserId =  ( (int)userDao.count() + 1);
        System.out.println("newUserId = " + newUserId);

        // Add new user to users table
        userDao.save(new User(user.getUsername(), user.getPassword(), user.getEmail(), user.getState(), user.getCity(), user.getDob(), user.getPhoneNumber(), ConvertStringToDate(user.getDob()), user.getLastLogin(), user.getGender()));
        smokerDao.save( new SmokerInfo(String.valueOf(newUserId),smokerInfo.getDay_quit_smoking(), smokerInfo.getCost_of_cigs_saved()) );

        return "login";
    }

    public Date ConvertStringToDate(String string) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date ConvertedDate = sdf.parse(string);

        return ConvertedDate;
    }

}
