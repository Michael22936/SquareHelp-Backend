package com.squarehelp.squarehelp.repositories;

import com.squarehelp.squarehelp.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    @Query(value = "select id, author_user_id, recipient_user_id, message, user_id, last_updated, recipient_username FROM messages where recipient_user_id = :id", nativeQuery = true)
    List<Messages> findMessagesByRecipient_user_idIs(@Param("id") Long id);

    @Query(value = "select id, author_user_id, recipient_user_id, message, user_id, last_updated, recipient_username FROM messages where recipient_user_id = :id OR author_user_id = :id", nativeQuery = true)
    List<Messages> findDistinctByRecipient_user_idOrAuthor_user_id(@Param("id") Long id);

    @Query(value = "select distinct id, author_user_id, recipient_user_id, message, user_id, last_updated, recipient_username FROM messages where recipient_user_id = :id", nativeQuery = true)
    List<Messages> findDistinctByAuthor_user_id(@Param("id") Long id);
}
