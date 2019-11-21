package com.squarehelp.squarehelp.repositories;

import com.squarehelp.squarehelp.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
}
