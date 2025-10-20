package com.identityHub.otpservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "IDHUB_USR_OTP")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class userOTPEntity
{

    @Id
    private Long usrId;

    private String idValue;

    private String idType;

    private Long usrOtp;

    private Date genTs;

    private Date expTs;

    private boolean verified;

    @PrePersist
    protected void onCreate() {
        genTs = new Date();
        expTs = new Date(genTs.getTime() + 5 * 60 * 1000); // +5 min
    }
}
