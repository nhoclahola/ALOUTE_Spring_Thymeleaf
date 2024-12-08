package com.nhoclahola.socialnetworkv1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImplementation implements EmailService
{
    private final JavaMailSender mailSender;

    @Override
    public void sendConfirmationEmail(String toEmail) {
        // Tạo URL xác nhận, chỉ cần truyền email trong tham số URL
        String confirmationUrl = "http://api.social-network-v1.kesug.com" + "/verify-email?email=" + toEmail;

        // Tạo email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Xác nhận đăng ký tài khoản");
        message.setText("Cảm ơn bạn đã đăng ký. Vui lòng xác nhận email của bạn bằng cách nhấn vào đường link sau: " + confirmationUrl);

        // Gửi email
        mailSender.send(message);
    }
}