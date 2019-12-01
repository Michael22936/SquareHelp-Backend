package com.squarehelp.squarehelp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "smoking_info")
public class SmokerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "DATE")
    private String day_quit_smoking;

    @Column(columnDefinition = "DATE")
    private Date day_relapse;

    @Column(columnDefinition = "BIGINT(20)")
    private int total_days_smoke_free;

    @Column(columnDefinition = "INT(10)")
    private int points;

    @Column(columnDefinition = "INT(10)")
    private int cost_of_cigs_saved;

    @OneToOne(mappedBy = "smokerInfo")
    @JsonBackReference
    private User user;


    public SmokerInfo() {}

    public SmokerInfo(long id, String day_quit_smoking, Date day_relapse, int total_days_smoke_free, int points, int cost_of_cigs_saved) {
        this.id = id;
        this.day_quit_smoking = day_quit_smoking;
        this.day_relapse = day_relapse;
        this.total_days_smoke_free = total_days_smoke_free;
        this.points = points;
        this.cost_of_cigs_saved = cost_of_cigs_saved;
    }

    public SmokerInfo(String day_quit_smoking, Date day_relapse, int total_days_smoke_free, int points, int cost_of_cigs_saved) {
        this.day_quit_smoking = day_quit_smoking;
        this.day_relapse = day_relapse;
        this.total_days_smoke_free = total_days_smoke_free;
        this.points = points;
        this.cost_of_cigs_saved = cost_of_cigs_saved;
    }

    public SmokerInfo(String day_quit_smoking, int cost_of_cigs_saved, User user) {
        this.day_quit_smoking = day_quit_smoking;
        this.cost_of_cigs_saved = cost_of_cigs_saved;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDay_quit_smoking() {
        return day_quit_smoking;
    }

    public void setDay_quit_smoking(String day_quit_smoking) {
        this.day_quit_smoking = day_quit_smoking;
    }

    public Date getDay_relapse() {
        return day_relapse;
    }

    public void setDay_relapse(Date day_relapse) {
        this.day_relapse = day_relapse;
    }

    public int getTotal_days_smoke_free() {
        return total_days_smoke_free;
    }

    public void setTotal_days_smoke_free(int total_days_smoke_free) {
        this.total_days_smoke_free = total_days_smoke_free;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCost_of_cigs_saved() {
        return cost_of_cigs_saved;
    }

    public void setCost_of_cigs_saved(int cost_of_cigs_saved) {
        this.cost_of_cigs_saved = cost_of_cigs_saved;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}