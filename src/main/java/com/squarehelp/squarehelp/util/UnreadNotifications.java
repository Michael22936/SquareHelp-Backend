package com.squarehelp.squarehelp.util;

import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.repositories.NotificationRepository;


import java.util.List;

public class UnreadNotifications {
    public static int unreadNotificationsCount(NotificationRepository notifyDao, Long user_id){
    List<Notification> UnreadAlertList = notifyDao.findNotificationsUnread(user_id); // // finds list of unread notifications
        int unreadCount = UnreadAlertList.size();
                if (unreadCount <= 0){
            return 0;
        }else {
        return unreadCount;
        }
    }
}

