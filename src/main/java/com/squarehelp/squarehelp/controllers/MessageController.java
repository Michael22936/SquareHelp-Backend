package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.Messages;
import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.MessagesRepository;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import com.squarehelp.squarehelp.services.NotificationServices;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessageController {
    private final MessagesRepository messageDao;
    private final UserRepository userDao;
    private final SmokerInfoRepository smokeDao;
    private final NotificationServices notiServices;
    private final NotificationRepository notiDao;

    public MessageController(MessagesRepository messageDao, UserRepository userDao, SmokerInfoRepository smokeDao, NotificationServices notiServices, NotificationRepository notiDao){
        this.messageDao = messageDao;
        this.userDao = userDao;
        this.smokeDao = smokeDao;
        this.notiServices = notiServices;
        this.notiDao = notiDao;
    }

    // View all messages and shows unique authors
    @GetMapping("/message")
    public String getSendMessageView(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        List<Messages> m = messageDao.findMessagesByRecipient_user_idIs(id);

        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("uniqueMsgs", m);

        return "message-view-all";
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
        notiServices.createNotification(user.getUsername(), rId, "msg");
        return "redirect:/profile/" + id;
    }

    @PostMapping("/messagechat/{oId}")
    public String getMessageChat(Model model, @PathVariable long oId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        Long notification = notiDao.findNotificationsByOriginator_user_idIs(oId);
        User recip =  userDao.getOne(notification);
        SmokerInfo smokerInfo = smokeDao.getOne(id);
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("messages", messageDao.findMessagesByRecipient_user_idIs(id));
        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("recipId", recip);

        return "messagechat";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<User> sendMatchingUser(@RequestParam String username){
        System.out.println(username);
        System.out.println(userDao.findByUsernameContaining(username));
        return userDao.findByUsernameContaining(username);
    }
}


