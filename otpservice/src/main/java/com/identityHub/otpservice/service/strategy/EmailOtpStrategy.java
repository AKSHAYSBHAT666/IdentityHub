package com.identityHub.otpservice.service.strategy;

import com.identityHub.otpservice.DTO.identityDTO;
import com.identityHub.otpservice.DTO.otpResponseDTO;
import com.identityHub.otpservice.utility.OTPUtility;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service("emailOtpService")
@RequiredArgsConstructor
public class EmailOtpStrategy implements OtpStrategy
{
    @Value("${spring.mail.properties.mail.smtp.from}")
    private String fromEmail;
    private final JavaMailSender mailSender;

    private void sendEmail(String toEmail, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

        messageHelper.setFrom(fromEmail);
        messageHelper.setTo(toEmail);
        messageHelper.setSubject(subject);
        messageHelper.setText(content);

        mailSender.send(message);
    }

    @Override
    public otpResponseDTO generate(identityDTO dto) throws MessagingException {
        Long otp = OTPUtility.generateOtp();
        String otpString = String.valueOf(otp);

        sendEmail(dto.getIdValue(), "Your OTP is:", otpString);

        return new otpResponseDTO(dto.getIdValue(), dto.getIdType(), true, otp);
    }
}
