package com.squarehelp.squarehelp.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;
import javax.persistence.*;

@Entity
@Table(name = "verifications_req")
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "INT")
    private int originator_user_id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String approver_name;

    @Column(columnDefinition = "DATE")
    private String day_created;

    @Column(columnDefinition = "INT")
    private int days_smoke_free;

    @Column(columnDefinition = "BOOLEAN")
    private String is_approved;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user_veq;

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

    public String getApprover_name() {
        return approver_name;
    }

    public void setApprover_name(String approver_name) {
        this.approver_name = approver_name;
    }

    public String getDay_created() {
        return day_created;
    }

    public void setDay_created(String day_created) {
        this.day_created = day_created;
    }

    public int getDays_smoke_free() {
        return days_smoke_free;
    }

    public void setDays_smoke_free(int days_smoke_free) {
        this.days_smoke_free = days_smoke_free;
    }

    public String getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(String is_approved) {
        this.is_approved = is_approved;
    }

    public User getUser_veq() {
        return user_veq;
    }

    public void setUser_veq(User user_veq) {
        this.user_veq = user_veq;
    }

    public Verification(int originator_user_id, String approver_name, String day_created, int days_smoke_free, String is_approved, User user_veq) {
        this.originator_user_id = originator_user_id;
        this.approver_name = approver_name;
        this.day_created = day_created;
        this.days_smoke_free = days_smoke_free;
        this.is_approved = is_approved;
        this.user_veq = user_veq;
    }

}
