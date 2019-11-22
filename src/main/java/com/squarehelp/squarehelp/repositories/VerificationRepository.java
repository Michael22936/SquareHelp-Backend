package com.squarehelp.squarehelp.repositories;

import com.squarehelp.squarehelp.models.SmokerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<SmokerInfo, Long> {
}
