package com.squarehelp.squarehelp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "BIGINT(20)")
    private String recipient_user_id;

    @Column(columnDefinition = "BIGINT(20)")
    private String originator_user_id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String notification;

    @Column(columnDefinition = "BOOLEAN")
    private boolean is_viewed;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user_noti;


    public Notification(String recipient_user_id, String originator_user_id, String notification, boolean is_viewed) {
        this.recipient_user_id = recipient_user_id;
        this.originator_user_id = originator_user_id;
        this.notification = notification;
        this.is_viewed = is_viewed;
    }

    public Notification() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecipient_user_id() {
        return recipient_user_id;
    }

    public void setRecipient_user_id(String recipient_user_id) {
        this.recipient_user_id = recipient_user_id;
    }

    public String getOriginator_user_id() {
        return originator_user_id;
    }

    public void setOriginator_user_id(String originator_user_id) {
        this.originator_user_id = originator_user_id;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public boolean getIs_viewed() {return is_viewed; }

    public void setIs_viewed(boolean is_viewed) {
        this.is_viewed = is_viewed;
    }
    public User getUser_noti() {
        return user_noti;
    }

    public void setUser_noti(User user_noti) {
        this.user_noti = user_noti;
    }
}
