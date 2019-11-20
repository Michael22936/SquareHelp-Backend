package com.squarehelp.squarehelp.repositories;

import com.squarehelp.squarehelp.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
