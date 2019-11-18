package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final UserRepository userDao;

   public LoginController(UserRepository userDao){
       this.userDao = userDao;
   }


    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> login() {
        return userDao.findAll();
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam String username, @RequestParam String password, @RequestParam String _csrf){
       User u = userDao.findByUsername(username);
       if (u != null){
           return "valid";
       }
       return "not valid";
    }

}
