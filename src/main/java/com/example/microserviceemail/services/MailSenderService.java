package com.example.microserviceemail.services;

import com.example.microserviceemail.entity.dtos.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender mailSender;
    public void sendMail(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getContent());
        message.setFrom(emailDTO.getOwner());
        message.setTo(emailDTO.getAddressee());
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
