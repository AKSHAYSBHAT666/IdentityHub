package com.identityHub.otpservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class otpResponseDTO {
    private String idValue;
    private String idType;
    private boolean verified;
    private Long otp;
}