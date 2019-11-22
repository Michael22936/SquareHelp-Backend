package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.UserDataHandler;

import java.util.List;

@Controller
public class NotificationController {

    private final UserRepository userDao;
    private final NotificationRepository notiDao;

    public NotificationController(UserRepository userDao, NotificationRepository notiDao){
        this.notiDao = notiDao;
        this.userDao = userDao;
    }

    // Goto the page to see the actual notifications
    @GetMapping("/notifications/{id}")
    public String showNotifications(@PathVariable long id, Model model){
        //Get current user
        User u = userDao.findUserById(id);

        List<Notification> n = notiDao.findNotificationsByRecipient_user_idIs(id);

        String uidStr = String.valueOf(u.getId());

        System.out.println("uidStr = " + uidStr);

        model.addAttribute("uid", uidStr);
        model.addAttribute("notifications", n);
        return "notification";
    }

    // Need post mapping to update boolean
//    @PostMapping("/notifications/{id}")
//    public String updateNotifications(@PathVariable long id, Model model){
//        List<Notification> n = notiDao.findNotificationsByRecipient_user_idIs(id);
//        model.addAttribute("notifications", n);
//        notiDao.save(new )
//    }

}
