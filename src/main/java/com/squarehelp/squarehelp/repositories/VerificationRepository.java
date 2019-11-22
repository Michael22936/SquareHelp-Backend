package com.squarehelp.squarehelp.repositories;

import com.squarehelp.squarehelp.models.Notification;
import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationRepository extends JpaRepository<SmokerInfo, Long> {

    //    for current user sending verification forms
    @Query(value = "SELECT id, originator_user_id, approver_user_id, day_created, days_smoke_free, is_approved  FROM verifications_req WHERE originator_user_id = :id ORDER BY id DESC LIMIT 30;", nativeQuery = true)
    User findVerificationsByApprover_user_idIs(@Param("id") Long id);



}
