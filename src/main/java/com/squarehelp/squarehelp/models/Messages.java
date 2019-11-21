package com.squarehelp.squarehelp.models;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "BIGINT(20)")
    private String author_user_id;

    @Column(columnDefinition = "BIGINT(20)")
    private String recipient_user_id;

    @Column(columnDefinition = "TEXT")
    private String message;

    public Messages(String author_user_id, String recipient_user_id, String message) {
        this.author_user_id = author_user_id;
        this.recipient_user_id = recipient_user_id;
        this.message = message;
    }

    public Messages() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor_user_id() {
        return author_user_id;
    }

    public void setAuthor_user_id(String author_user_id) {
        this.author_user_id = author_user_id;
    }

    public String getRecipient_user_id() {
        return recipient_user_id;
    }

    public void setRecipient_user_id(String recipient_user_id) {
        this.recipient_user_id = recipient_user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
