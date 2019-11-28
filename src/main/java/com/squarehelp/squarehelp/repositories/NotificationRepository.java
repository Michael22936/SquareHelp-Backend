package com.squarehelp.squarehelp.repositories;

import com.squarehelp.squarehelp.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT id, recipient_user_id, originator_user_id, notification, is_viewed, user_id FROM notifications WHERE originator_user_id = :id ORDER BY id DESC LIMIT 30;", nativeQuery = true)
    List<Notification> findNotificationsByRecipient_user_idIs(@Param("id") Long id);

    @Query(value = "SELECT id, recipient_user_id, originator_user_id, notification, is_viewed, user_id FROM notifications WHERE recipient_user_id = :id ORDER BY id DESC LIMIT 30;", nativeQuery = true)
    Long findNotificationsByOriginator_user_idIs(@Param("id") Long id);

    @Query(value = "SELECT id, recipient_user_id, originator_user_id, notification, is_viewed, user_id FROM notifications WHERE originator_user_id = :id AND is_viewed = false;", nativeQuery = true)
    List<Notification> findNotificationsUnread(@Param("id") Long id);

}
