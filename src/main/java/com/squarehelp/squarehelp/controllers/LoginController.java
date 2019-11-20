package com.squarehelp.squarehelp.controllers;

import com.google.gson.Gson;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/login")
@Controller
public class LoginController {

    private final UserRepository userDao;

    public LoginController(UserRepository userDao) {
        this.userDao = userDao;
    }

}
//    // Used to convert user object to JSON
//    // Just returns users.
//    private String convertUserToJson(User u) {
//        // Create output collection
//        Map<String, String> temp = new HashMap<>();
//
//        // Add values to temp object
//        temp.put("username", u.getUsername());
//        temp.put("email", u.getEmail());
//        temp.put("city", u.getCity());
//        temp.put("state", u.getState());
//        temp.put("dob", String.valueOf(u.getDob()));
//        temp.put("phone", u.getPhoneNumber());
//        temp.put("dateCreated", String.valueOf(u.getDateCreated()));
//
//        // Create new Gson object and add contents and convert to Json
//        Gson g = new Gson();
//        String out = g.toJson(temp);
//
//        return out;
//    }
//
//    // Just returns user with isLoggedIn = true
//    private String convertUserToJson(User u, Boolean loggedIn) {
//        // Create output collection
//        Map<String, String> temp = new HashMap<>();
//
//        // Add values to temp object
//        temp.put("isLoggedIn", "true");
//        temp.put("username", u.getUsername());
//        temp.put("email", u.getEmail());
//        temp.put("city", u.getCity());
//        temp.put("state", u.getState());
//        temp.put("dob", String.valueOf(u.getDob()));
//        temp.put("phone", u.getPhoneNumber());
//        temp.put("dateCreated", String.valueOf(u.getDateCreated()));
//
//        // Create new Gson object and add contents and convert to Json
//        Gson g = new Gson();
//        String out = g.toJson(temp);
//
//        return out;
//    }
//
//    private String convertUserToJson(Boolean loggedIn) {
//        if (loggedIn == false) {
//            Map<String, String> temp = new HashMap<>();
//
//            temp.put("isLoggedIn", "false");
//
//            // Create new Gson object and add contents and convert to Json
//            Gson g = new Gson();
//            String out = g.toJson(temp);
//
//            return out;
//        } else {
//            return "Call this method with a User as a parameter";
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String getUserLogin() {
//        return "Please send a post request to this address";
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public String userLogin(@RequestParam String username, @RequestParam String password, @RequestParam String _csrf) {
//        User u = userDao.findByUsername(username);
//
//        if (u != null && password.equalsIgnoreCase(u.getPassword())) {
//            String userToJson = convertUserToJson(u, true);
//            return userToJson;
//        } else {
//            String failedLogin = convertUserToJson(false);
//            return failedLogin;
//        }
//    }
