package com.identityHub.otpservice.service;

import com.identityHub.otpservice.DTO.identityDTO;
import com.identityHub.otpservice.DTO.otpRequestDTO;
import com.identityHub.otpservice.DTO.otpResponseDTO;
import com.identityHub.otpservice.entity.userOTPEntity;
import com.identityHub.otpservice.repository.otpRepository;
import com.identityHub.otpservice.utility.OTPUtility;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
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

        entity.setUsrOtp(response.getOtp());
        entity.setIdValue(response.getIdValue());
        entity.setVerified(response.isVerified());
        entity.setIdType(response.getIdType());
        entity.setUsrId(dto.getUsrId());

        OtpRepository.save(entity);
        return response;
    }

    public otpResponseDTO verifyOTPforidentity(otpRequestDTO dto) throws EntityNotFoundException
    {
        Long usrOtp = OtpRepository.findUsrOtpByIdValueAndIdType(dto.getIdValue(), dto.getIdType());
        if (null == usrOtp)
            throw new EntityNotFoundException("OTP not found for the given idValue and idType");

        otpResponseDTO response = new otpResponseDTO(dto.getIdValue(), dto.getIdType(), true, dto.getOtp());
        if (false == usrOtp.equals(dto.getOtp()))
            response.setVerified(false);

        return response;
    }
}
