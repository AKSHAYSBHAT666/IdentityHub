package com.identityHub.otpservice.service;

import com.identityHub.otpservice.DTO.identityDTO;
import com.identityHub.otpservice.DTO.otpResponseDTO;
import com.identityHub.otpservice.entity.userOTPEntity;
import com.identityHub.otpservice.repository.otpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class otpService {

    @Autowired
    private otpRepository OtpRepository;

    public otpResponseDTO createOTP(identityDTO dto)
    {
        otpResponseDTO response = new otpResponseDTO();

        userOTPEntity entity = new userOTPEntity();
        entity.setUsrOtp(dto.getUsrId() * 10);
        entity.setIdValue(dto.getIdValue());
        entity.setVerified(true);
        entity.setIdType(dto.getIdType());
        entity.setUsrId(dto.getUsrId());
        OtpRepository.save(entity);

        response.setIdValue(dto.getIdValue());
        response.setVerified(true);
        response.setIdType(dto.getIdType());
        response.setOtp(dto.getUsrId() * 10);


        return response;
    }
}
