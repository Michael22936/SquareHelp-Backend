package com.squarehelp.squarehelp.models;

import java.sql.Date;

public class MessagesUnique {

    private long id;
    private String username;
    private Date last_updated;

    public MessagesUnique(long id, String username, Date last_updated) {
        this.id = id;
        this.username = username;
        this.last_updated = last_updated;
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

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }

}
