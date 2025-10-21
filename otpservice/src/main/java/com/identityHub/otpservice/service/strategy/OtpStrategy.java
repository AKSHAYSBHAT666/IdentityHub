package com.identityHub.otpservice.service.strategy;

import com.identityHub.otpservice.DTO.identityDTO;
import com.identityHub.otpservice.DTO.otpResponseDTO;
import jakarta.mail.MessagingException;

public interface OtpStrategy
{
    otpResponseDTO generate(identityDTO dto) throws MessagingException;
}
