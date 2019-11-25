package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {

    private final UserRepository userDao;

    public AuthenticationController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginToSite(@RequestParam String username, @RequestParam String password) throws NullPointerException {

        User u = new User();

        try {
            u = userDao.findByUsername(username);

            // If user is found, perform logic.
//            if (password.equalsIgnoreCase(u.getPassword())) {
                System.out.println("Password verification is successful");
//            } else {
//                System.out.println("no go");
//                return "login";
//            }
        } catch (NullPointerException e) {
            // If user is not found, redirect to login page.
//            return "login";
        }

                return "redirect:/dashboard/" + u.getId();
    }
}
