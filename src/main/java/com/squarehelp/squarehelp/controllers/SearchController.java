package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SearchController {

    private final UserRepository userDao;
    private final SmokerInfoRepository smokeDao;

    public SearchController(UserRepository userDao, SmokerInfoRepository smokeDao) {
        this.userDao = userDao;
        this.smokeDao = smokeDao;
    }

    public List<User> searchUsers(String query) {
        return userDao.findByUsernameContaining(query);
    }
}

