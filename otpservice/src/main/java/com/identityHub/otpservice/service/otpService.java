package com.identityHub.otpservice.service;

import com.identityHub.otpservice.DTO.identityDTO;
import com.identityHub.otpservice.DTO.otpResponseDTO;
import com.identityHub.otpservice.entity.userOTPEntity;
import com.identityHub.otpservice.repository.otpRepository;
import com.identityHub.otpservice.utility.OTPUtility;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class otpService {

    @Autowired
    private otpRepository OtpRepository;

    @Autowired
    private OtpContext otpContext;

    public otpResponseDTO createOTPforidentity(identityDTO dto) throws MessagingException {
        otpResponseDTO response = otpContext.generateOtp(dto);
        userOTPEntity entity = new userOTPEntity();
        Long otp = OTPUtility.generateOtp();

        entity.setUsrOtp(otp);
        entity.setIdValue(dto.getIdValue());
        entity.setVerified(true);
        entity.setIdType(dto.getIdType());
        entity.setUsrId(dto.getUsrId());

        OtpRepository.save(entity);

        response.setIdValue(dto.getIdValue());
        response.setVerified(true);
        response.setIdType(dto.getIdType());
        response.setOtp(otp);

        return response;
    }
}
