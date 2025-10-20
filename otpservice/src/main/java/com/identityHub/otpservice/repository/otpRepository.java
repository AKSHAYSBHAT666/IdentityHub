package com.identityHub.otpservice.repository;


import com.identityHub.otpservice.entity.userOTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface otpRepository extends JpaRepository<userOTPEntity, Long> {
}