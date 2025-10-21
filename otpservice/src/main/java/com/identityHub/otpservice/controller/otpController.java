package com.identityHub.otpservice.controller;

import com.identityHub.otpservice.DTO.identityDTO;
import com.identityHub.otpservice.DTO.otpResponseDTO;
import com.identityHub.otpservice.service.otpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public ResponseEntity<?> generateOTP(@RequestBody identityDTO dto) {
        try {
            otpResponseDTO response = OtpService.createOTPforidentity(dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // For invalid or missing input data
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
        catch (Exception e) {
            // Catch-all fallback
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }
}
