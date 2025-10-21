package com.identityHub.otpservice.service;

import com.identityHub.otpservice.DTO.identityDTO;
import com.identityHub.otpservice.DTO.otpResponseDTO;
import com.identityHub.otpservice.service.strategy.OtpStrategy;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OtpContext {
    private final Map<String, OtpStrategy> strategies;

    @Autowired
    public OtpContext(Map<String, OtpStrategy> strategies) {
        this.strategies = strategies;
    }

    public otpResponseDTO generateOtp(identityDTO dto) throws MessagingException {
        String type = dto.getIdType().toLowerCase();
        OtpStrategy strategy = switch (type) {
            case "mobile" -> strategies.get("mobileOtpService");
            case "email" -> strategies.get("emailOtpService");
            default -> throw new IllegalArgumentException("Unsupported OTP type: " + type);
        };
        return strategy.generate(dto);
    }
}