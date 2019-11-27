package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.Messages;
import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.MessagesRepository;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class MessageController {
    private final MessagesRepository messageDao;
    private final UserRepository userDao;
    private final SmokerInfoRepository smokeDao;
    private final NotificationRepository notifyDao;

    public MessageController(MessagesRepository messageDao, UserRepository userDao, SmokerInfoRepository smokeDao, NotificationRepository notifyDao){
        this.messageDao = messageDao;
        this.userDao = userDao;
        this.smokeDao = smokeDao;
        this.notifyDao = notifyDao;
    }

    @GetMapping("/message")
    public String getSendMessageView(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("messages", messageDao.getOne(id));
        return "message";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<User> sendMatchingUser(@RequestParam String username){
        System.out.println(username);
        System.out.println(userDao.findByUsernameContaining(username));
        return userDao.findByUsernameContaining(username);
    }
//=========== Address for notification fetch
    @GetMapping("/unreadAlert")
    @ResponseBody
    public List<Notification> sendMessageCount(){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // User logged in
//        long id = user.getId();
        List<Notification> listUnreadAlerts = notifyDao.countUnreadNotifications( 1L); // Matt
        List<Notification> yaelUnreadAlertList = notifyDao.findNotificationsUnread(1L); // Yael
        System.out.println("Yaels list count = " + yaelUnreadAlertList.size());
        System.out.println("Matt list count = " + listUnreadAlerts.size());
        for (Notification alert: listUnreadAlerts) {
            System.out.println("alert boolean = " + alert.getIs_viewed());
            System.out.println("alert Recipient = " + alert.getRecipient_user_id());
            System.out.println("alert Recipient = " + alert.getUser_noti().getUsername());
            System.out.println(alert);

        }
//        System.out.println("notifyDao.findNotificationsUnread(id) size = " + notifyDao.findNotificationsUnread(1L).size());
//        System.out.println("notifyDao.findAllById(Collections.singleton(id)) = " + notifyDao.findAllById(Collections.singleton(id)));
        return notifyDao.findAll();
    }

    @GetMapping("/message/{rId}")
    public String sendAMessageToAnotherUser(@PathVariable long rId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        return "redirect:/message/" + rId + "/" + id + "/send";
    }

    @GetMapping("/message/{rId}/send")
    public String sendMessage(Model model, @PathVariable long rId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("user", userDao.getOne(id));
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("recipient", userDao.getOne(rId));

        return "sendMessage";
    }

    @PostMapping("/message/{rId}/send")
    public String SaveMessage( @PathVariable long rId, @RequestParam String message) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        messageDao.save(new Messages((int)id,(int) rId, message, user));
        return "redirect:/profile/" + id;
    }
}