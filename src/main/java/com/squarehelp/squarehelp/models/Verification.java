package com.squarehelp.squarehelp.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;


@Entity
@Table(name = "verifications_req")
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @OneToMany(mappedBy = "user_id")
    @Column(columnDefinition = "INT")
    private int originator_user_id;

//    @OneToMany(mappedBy = "user_id")
    @Column(columnDefinition = "INT")
    private int approver_user_id;

    @Column(columnDefinition = "INT")
    private int day_created;

    @Column(columnDefinition = "INT")
    private int days_smoke_free;

    @Column(columnDefinition = "BOOLEAN")
    private boolean is_approved;


    public Verification(int originator_user_id, int approver_user_id, int day_created, int days_smoke_free, boolean is_approved) {
        this.originator_user_id = originator_user_id;
        this.approver_user_id = approver_user_id;
        this.day_created = day_created;
        this.days_smoke_free = days_smoke_free;
        this.is_approved = is_approved;
    }

    public Verification() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOriginator_user_id() {
        return originator_user_id;
    }

    public void setOriginator_user_id(int originator_user_id) {
        this.originator_user_id = originator_user_id;
    }

    public int getApprover_user_id() {
        return approver_user_id;
    }

    public void setApprover_user_id(int approver_user_id) {
        this.approver_user_id = approver_user_id;
    }

    public int getDay_created() {
        return day_created;
    }

    public void setDay_created(int day_created) {
        this.day_created = day_created;
    }

    public int getDays_smoke_free() {
        return days_smoke_free;
    }

    public void setDays_smoke_free(int days_smoke_free) {
        this.days_smoke_free = days_smoke_free;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }

}
