package com.squarehelp.squarehelp.services;

import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationServices {

    private final NotificationRepository notiDao;
    private final user

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
