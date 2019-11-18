package com.squarehelp.squarehelp.models;



import javax.persistence.*;

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
    @Column(columnDefinition = "INT(5)")
    private int dob;
    @Column(columnDefinition = "VARCHAR(15) NOT NULL UNIQUE")
    private String phoneNumber;
    @Column(columnDefinition = "INT(11)")
    private int dateCreated;
    @Column(columnDefinition = "VARCHAR(255)")
    private String lastLogin;
    @Column(columnDefinition = "VARCHAR(255)")
    private String gender;

    public User() {
    }

    public User(long id, String username, String password, String email, String state, String city, int dob, String phoneNumber, int dateCreated, String lastLogin, String gender) {
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

    public User(String username, String password, String email, String state, String city, int dob, String phoneNumber, int dateCreated, String lastLogin, String gender) {
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

    public int getDob() {
        return dob;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(int dateCreated) {
        this.dateCreated = dateCreated;
    }

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
}
