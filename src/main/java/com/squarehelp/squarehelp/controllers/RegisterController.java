package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private final UserRepository userDao;

    @Autowired
    private final SmokerInfoRepository smokerDao;

    public RegisterController(UserRepository userDao, SmokerInfoRepository smokerDao) {
        this.userDao = userDao;
        this.smokerDao = smokerDao;
    }

    private String generateRandomId() {
        Random r = new Random();
        int randy = ThreadLocalRandom.current().nextInt();
        String out = String.valueOf(randy);
        return out;
    }

    // Salt Generation for hashing password
    private String salt = BCrypt.gensalt(10);

    // Today's date generated in julian format
    private int todaysJulianDate = 19323;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveNewUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String dob,
            @RequestParam String phone,
            @RequestParam String gender,
            @RequestParam String dateQuitSmoking,
            @RequestParam String costOfCigs,
            @RequestParam String city,
            @RequestParam String state) {

        // Setup
        Long newUserId = Long.parseLong(generateRandomId());
        String hashedPw = BCrypt.hashpw(password, salt);
        String today = String.valueOf(todaysJulianDate);


        // Create new user from parameters
        User u = new User(
                newUserId,
                username,
                hashedPw,
                email,
                state,
                city,
                Integer.parseInt(dob),
                phone,
                Integer.parseInt(today),
                today,
                gender
        );

        // Create a new smoker_info from parameters
        SmokerInfo s = new SmokerInfo(
                String.valueOf(newUserId),
                Integer.parseInt(dateQuitSmoking),
                0,
                1,
                0,
                Integer.parseInt(costOfCigs)
        );

        // Save both new user and smoker_info for new user
        userDao.save(u);
        smokerDao.save(s);

        // Return http status 200 (Okay)
        return new ResponseEntity(HttpStatus.OK);
    }
}
