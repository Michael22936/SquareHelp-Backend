package com.squarehelp.squarehelp.controllers;

import com.google.gson.Gson;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserRepository userDao;

    public LoginController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginToSite(@RequestParam String username, @RequestParam String password) throws NullPointerException {

        User u = new User();

        try {
            u = userDao.findByUsername(username);

            // If user is found, perform logic.
            if (password.equalsIgnoreCase(u.getPassword())) {
                System.out.println("Password verification is successful");
                return "redirect:/dashboard/" + u.getId();
            } else {
                System.out.println("no go");
                return "login";
            }
        } catch (NullPointerException e) {
            // If user is not found, redirect to login page.
            return "login";
        }

    }
}