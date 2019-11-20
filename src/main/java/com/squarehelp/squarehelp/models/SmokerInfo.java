package com.squarehelp.squarehelp.models;

import javax.persistence.*;

@Entity
@Table(name = "smoking_info")
public class SmokerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "INT UNIQUE")
    private String user_id;

    @Column(columnDefinition = "INT(5)")
    private int day_quit_smoking;

    @Column(columnDefinition = "INT(5)")
    private int day_relapse;

    @Column(columnDefinition = "BIGINT(20)")
    private int total_days_smoke_free;

    @Column(columnDefinition = "INT(10)")
    private int points;

    @Column(columnDefinition = "INT(10)")
    private int cost_of_cigs_saved;

    public SmokerInfo() {}

    public SmokerInfo(long id, String user_id, int day_quit_smoking, int day_relapse, int total_days_smoke_free, int points, int cost_of_cigs_saved) {
        this.id = id;
        this.user_id = user_id;
        this.day_quit_smoking = day_quit_smoking;
        this.day_relapse = day_relapse;
        this.total_days_smoke_free = total_days_smoke_free;
        this.points = points;
        this.cost_of_cigs_saved = cost_of_cigs_saved;
    }

    public SmokerInfo(String user_id, int day_quit_smoking, int day_relapse, int total_days_smoke_free, int points, int cost_of_cigs_saved) {
        this.user_id = user_id;
        this.day_quit_smoking = day_quit_smoking;
        this.day_relapse = day_relapse;
        this.total_days_smoke_free = total_days_smoke_free;
        this.points = points;
        this.cost_of_cigs_saved = cost_of_cigs_saved;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getDay_quit_smoking() {
        return day_quit_smoking;
    }

    public void setDay_quit_smoking(int day_quit_smoking) {
        this.day_quit_smoking = day_quit_smoking;
    }

    public int getDay_relapse() {
        return day_relapse;
    }

    public void setDay_relapse(int day_relapse) {
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
}