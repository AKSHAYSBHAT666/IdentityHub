package com.identityHub.otpservice.repository;


import com.identityHub.otpservice.entity.userOTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface otpRepository extends JpaRepository<userOTPEntity, Long>
{
    @Query("SELECT u.usrOtp FROM userOTPEntity u WHERE u.idValue = :idValue AND u.idType = :idType")
    Long findUsrOtpByIdValueAndIdType(String idValue, String idType);
}