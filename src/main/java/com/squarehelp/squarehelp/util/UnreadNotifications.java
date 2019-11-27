package com.squarehelp.squarehelp.util;

import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.repositories.NotificationRepository;


import java.util.List;



public class UnreadNotifications {




    public static int unreadNotificationsCount(NotificationRepository notifyDao, Long user_id){

    List<Notification> UnreadAlertList = notifyDao.findNotificationsUnread(user_id); // // finds list of unread notifications
        int unreadCount = UnreadAlertList.size();
        System.out.println("==============Hello from inside method I created :) =================");
        System.out.println("logged in users unread alert count = " + UnreadAlertList.size());

        if (unreadCount <= 0){
            return 0;
        }else {
        return unreadCount;
        }

    }

}


//    public DashboardController(SmokerInfoRepository smokeDao, UserRepository userDao, NotificationRepository notifyDao) {
//        this.smokeDao = smokeDao;
//        this.userDao = userDao;
//        this.notifyDao = notifyDao;
//    }