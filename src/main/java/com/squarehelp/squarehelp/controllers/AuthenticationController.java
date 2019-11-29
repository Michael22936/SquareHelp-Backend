package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {

    private final UserRepository userDao;
    private final SmokerInfoRepository smokeDao;

    public AuthenticationController(UserRepository userDao, SmokerInfoRepository smokeDao) {
        this.userDao = userDao;
        this.smokeDao = smokeDao;
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

                System.out.println("Password verification is successful");
        } catch (NullPointerException e) {
            // If user is not found, redirect to login page.
            return "login";
        }

                return "redirect:/dashboard/" + u.getId();
    }

    @GetMapping("/logout")
    public String logout(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("smoke", smokeDao.getOne(id));
        return "logout";
    }
}
