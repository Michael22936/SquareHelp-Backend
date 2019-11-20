package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotificationController {

    private final UserRepository userDao;
    private final NotificationRepository notiDao;

    public NotificationController(UserRepository userDao, NotificationRepository notiDao){
        this.notiDao = notiDao;
        this.userDao = userDao;
    }

    @GetMapping("/notification/{id}")
    public String show(@PathVariable long id, Model model){
        model.addAttribute("notifications", userDao.getOne(id));
        return "notification";
    }


}
