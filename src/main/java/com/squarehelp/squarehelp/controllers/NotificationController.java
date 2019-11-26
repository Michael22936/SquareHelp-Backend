package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.MessagesRepository;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.UserDataHandler;

import java.util.List;

import static com.squarehelp.squarehelp.util.Calculator.avgPointsCalculator;
import static com.squarehelp.squarehelp.util.Calculator.calcMoneySaved;

@Controller
public class NotificationController {

    private final UserRepository userDao;
    private final NotificationRepository notiDao;
    private final SmokerInfoRepository smokeDao;
    private final MessagesRepository messageDao;

    public NotificationController(UserRepository userDao, NotificationRepository notiDao, SmokerInfoRepository smokeDao, MessagesRepository messageDao){
        this.notiDao = notiDao;
        this.userDao = userDao;
        this.smokeDao = smokeDao;
        this.messageDao = messageDao;
    }

    @GetMapping("/notifications")
    public String passingDashboard(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        List<Notification> n = notiDao.findNotificationsByRecipient_user_idIs(id);

        // Mark all notifications read and save them.
        for (Notification noti : n) {
            noti.setIs_viewed(true);
            notiDao.save(noti);
        }

        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("users", user);
        model.addAttribute("uid", String.valueOf(user.getId()));
        model.addAttribute("notifications", n);
        model.addAttribute("messages", messageDao.getOne(id));
        return "notification";
    }

    // Get number of unread notifications
    public int getUnreadNotifications(Long id) {
        List<Notification> n = notiDao.findNotificationsUnread(id);
        return n.size();
    }

    /*
    *   Creates notification in the notification table.
    *
    *   Username - (str) - username of the recipient
    *   uid - (long) - user ID of the author of the notification
    *   type - (str) - Type of message depending on the type of notification
    *       "msg" - For messages
    *       "veri" - For smoke request verifications
    */
    public void createNotification(String username, Long uid, String type) {
        // Verify all fields are there before running
        if (username == null || uid == null || type == null) {
            return;
        } else if (username == null && uid == null && type == null) {
            return;
        } else {
            Notification n = new Notification();

            User recipient = userDao.findByUsername(username);

            switch(type) {
                case ("msg"):
                    n.setRecipient_user_id(String.valueOf(recipient.getId()));
                    n.setOriginator_user_id(String.valueOf(uid));
                    n.setNotification("You have a new message from " + username);
                    break;
                case ("veri"):
                    n.setRecipient_user_id(String.valueOf(recipient.getId()));
                    n.setOriginator_user_id(String.valueOf(uid));
                    n.setNotification("You have a smoke verification request from " + username);
                    break;
                default:
                    return;
            }

            n.setIs_viewed(false);

            notiDao.save(n);
        }
    }

}
