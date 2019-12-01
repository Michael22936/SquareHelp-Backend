package com.squarehelp.squarehelp.models;

import java.sql.Date;

public class MessagesUnique {

    private long id;
    private String username;

    public MessagesUnique(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
