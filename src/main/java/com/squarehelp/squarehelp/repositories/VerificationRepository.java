package com.squarehelp.squarehelp.repositories;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {
    // Find all verifications by a particular user
    @Query(value = "SELECT id, originator_user_id, approver_name, day_created, days_smoke_free, is_approved, user_id, is_pending, is_changes_updated, sender_name FROM verifications_req WHERE originator_user_id = :id ORDER BY id DESC LIMIT 30", nativeQuery = true)
    List<Verification> findAllByOriginator_user_id(@Param("id") Long id);

    // Find all verifications by a particular approver
    @Query(value = "SELECT id, originator_user_id, approver_name, day_created, days_smoke_free, is_approved, user_id, is_pending, is_changes_updated, sender_name FROM verifications_req WHERE approver_name = :approverName ORDER BY id DESC LIMIT 30", nativeQuery = true)
    List<Verification> findAllByApprover_name(@Param("approverName") String approverName);

    // Find one verifications by id
    @Query(value = "SELECT id, originator_user_id, approver_name, day_created, days_smoke_free, is_approved, user_id, is_pending, is_changes_updated, sender_name FROM verifications_req WHERE id = :veriId", nativeQuery = true)
    Verification findById(@Param("veriId") int veriId);
}
