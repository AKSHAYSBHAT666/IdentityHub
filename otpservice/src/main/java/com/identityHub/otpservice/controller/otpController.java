package com.identityHub.otpservice.controller;

import com.identityHub.otpservice.DTO.identityDTO;
import com.identityHub.otpservice.DTO.otpRequestDTO;
import com.identityHub.otpservice.DTO.otpResponseDTO;
import com.identityHub.otpservice.service.otpService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/otp")
public class otpController
{
    @Autowired
    private otpService OtpService;

    @PostMapping("/v1/create")
    public ResponseEntity<?> generateOTP(@RequestBody identityDTO dto) {
        try {
            otpResponseDTO response = OtpService.createOTPforidentity(dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // For invalid or missing input data
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (MessagingException e) {
            throw new MailSendException("Error sending OTP mail", e);
        }
        catch (Exception e) {
            // Catch-all fallback
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }

    @PostMapping("/v1/verify")
    public ResponseEntity<?> verifyOTP(@RequestBody otpRequestDTO dto) throws Exception {
        try {
            otpResponseDTO response = OtpService.verifyOTPforidentity(dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // For invalid or missing input data
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("error:"+e.getMessage());
        }
        catch (Exception e) {
            // Catch-all fallback
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }
}
