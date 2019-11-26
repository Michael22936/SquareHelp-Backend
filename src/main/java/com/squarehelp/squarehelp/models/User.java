package com.squarehelp.squarehelp.models;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL UNIQUE")
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(columnDefinition = "VARCHAR(2)")
    private String state;
    @Column(columnDefinition = "VARCHAR(255)")
    private String city;
    @Column(columnDefinition = "DATE")
    // change to local date
    private String dob;
    @Column(columnDefinition = "VARCHAR(15) NOT NULL UNIQUE")
    private String phoneNumber;
    @Column(columnDefinition = "DATE")
    private Date dateCreated;
    @Column(columnDefinition = "VARCHAR(255)")
    private String lastLogin;
    @Column(columnDefinition = "VARCHAR(255)")
    private String gender;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Messages> messages;
    @OneToMany(mappedBy = "user_veq", cascade = CascadeType.ALL)
    private List<Verification> verifications;
    @OneToMany(mappedBy = "user_noti", cascade = CascadeType.ALL)
    private List<Notification> notifications;
    @OneToOne
    @JsonManagedReference
    private SmokerInfo smokerInfo;


    public User() {
    }

    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public User(long id, String username, String password, String email, String state, String city, String dob, String phoneNumber, Date dateCreated, String lastLogin, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.state = state;
        this.city = city;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.dateCreated = dateCreated;
        this.lastLogin = lastLogin;
        this.gender = gender;
    }

    public User(String username, String password, String email, String state, String city, String dob, String phoneNumber, Date dateCreated, String lastLogin, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.state = state;
        this.city = city;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.dateCreated = dateCreated;
        this.lastLogin = lastLogin;
        this.gender = gender;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDob() { return dob; }

    public void setDob(String dob) { this.dob = dob; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateCreated() { return dateCreated; }

    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }
    public SmokerInfo getSmokerInfo() {
        return smokerInfo;
    }

    public void setSmokerInfo(SmokerInfo smokerInfo) {
        this.smokerInfo = smokerInfo;
    }
    public List<Verification> getVerifications() {
        return verifications;
    }

    public void setVerifications(List<Verification> verifications) {
        this.verifications = verifications;
    }
    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
