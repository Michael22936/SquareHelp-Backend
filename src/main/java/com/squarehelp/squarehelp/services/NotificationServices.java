package com.squarehelp.squarehelp.services;

import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class NotificationServices {

    private final NotificationRepository notiDao;
    private final UserRepository userDao;

    public NotificationServices(NotificationRepository notiDao, UserRepository userDao){
        this.notiDao = notiDao;
        this.userDao = userDao;
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Verify all fields are there before running
        if (username == null || uid == null || type == null) {
            System.out.println("hello");
            return;
        } else if (username == null && uid == null && type == null) {
            System.out.println("goodbye");
            return;
        } else {
            System.out.println("it worked");
            Notification n = new Notification();

            User recipient = userDao.findByUsername(username);

            switch(type) {
                case ("msg"):
                    n.setRecipient_user_id(String.valueOf(recipient.getId()));
                    n.setOriginator_user_id(String.valueOf(uid));
                    n.setUser_noti(user);
                    n.setNotification("You have a new message from " + username);
                    System.out.println("msg");
                    break;
                case ("veri"):
                    n.setRecipient_user_id(String.valueOf(recipient.getId()));
                    n.setOriginator_user_id(String.valueOf(uid));
                    n.setNotification("You have a smoke verification request from " + username);
                    break;
                default:
                    System.out.println("default");
                    return;
            }

            n.setIs_viewed(false);

            notiDao.save(n);
        }
    }

}
