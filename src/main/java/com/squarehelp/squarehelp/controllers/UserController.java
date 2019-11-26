package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

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
        user.setPassword(hash);
        Date d = new Date();
        user.setDateCreated(d);
        SmokerInfo si = smokeDao.save(smokerInfo);
        user.setSmokerInfo(si);
        userDao.save(user);
        return "redirect:/login";
    }
}
