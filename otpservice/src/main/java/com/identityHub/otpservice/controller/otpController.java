package com.identityHub.otpservice.controller;

import com.identityHub.otpservice.DTO.identityDTO;
import com.identityHub.otpservice.DTO.otpResponseDTO;
import com.identityHub.otpservice.service.otpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
public class otpController
{
    @Autowired
    private otpService OtpService;

    @PostMapping("/create")
    public ResponseEntity<otpResponseDTO> createIdentity(@RequestBody identityDTO dto) {
        otpResponseDTO response = OtpService.createOTP(dto);
        return ResponseEntity.ok(response);
    }
}
