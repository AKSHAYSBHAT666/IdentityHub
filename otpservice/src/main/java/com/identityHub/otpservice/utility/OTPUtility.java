package com.identityHub.otpservice.utility;

import java.security.SecureRandom;

public class OTPUtility {

    private static final SecureRandom random = new SecureRandom();

    // Generates a 4-digit OTP as a Long
    public static Long generateOtp() {
        int otp = 1000 + random.nextInt(9000); // ensures OTP is between 1000–9999
        return (long) otp;
    }
}