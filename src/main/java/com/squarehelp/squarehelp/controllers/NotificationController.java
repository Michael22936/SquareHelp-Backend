package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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

        List<Notification> n = notiDao.findNotificationsByRecipient_user_idIs(id);

        System.out.println("n.toString() = " + n.toString());

//        model.addAttribute("notifications", notiDao.findNotificationsByRecipient_user_idIs(id));
        return "notification";
    }

    // Need post mapping to update boolean

    // Show the number
    @GetMapping("/notifications/{id}/check")
    public Integer numOfNotifications(@PathVariable long id) {
        Notification n = notiDao.getOne(id);

        System.out.println("n = " + n.toString());

        return 1;
    };

}
