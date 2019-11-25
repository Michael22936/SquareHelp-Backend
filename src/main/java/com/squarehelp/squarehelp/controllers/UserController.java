package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private SmokerInfoRepository smokeDao;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, SmokerInfoRepository smokeDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.smokeDao = smokeDao;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("smokeInfo", new SmokerInfo());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user, @ModelAttribute SmokerInfo smokerInfo ){
        String hash = passwordEncoder.encode(user.getPassword());
        int newUserId =  ( (int)userDao.count() + 1);
        user.setPassword(hash);
        userDao.save(user);
        smokeDao.save( new SmokerInfo(String.valueOf(newUserId),smokerInfo.getDay_quit_smoking(), smokerInfo.getCost_of_cigs_saved()) );
        return "redirect:/login";
    }

    //    @PostMapping("/register")
//    public String RegisterNewUser(@ModelAttribute User user, ) throws ParseException {
//        // Pulls last user id and adds 1 to create user_id for smoking_into.
//
//        System.out.println("newUserId = " + newUserId);
//
//        // Add new user to users table
//        userDao.save(new User(user.getUsername(), user.getPassword(), user.getEmail(), user.getState(), user.getCity(), user.getDob(), user.getPhoneNumber(), ConvertStringToDate(user.getDob()), user.getLastLogin(), user.getGender()));
//
//
//        return "login";
//    }
//
//    public Date ConvertStringToDate(String string) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date ConvertedDate = sdf.parse(string);
//
//        return ConvertedDate;
//    }
}
