package com.example.microserviceemail.services;

import com.example.microserviceemail.entity.Email;
import com.example.microserviceemail.entity.dtos.EmailDTO;
import com.example.microserviceemail.enums.StatusEmail;
import com.example.microserviceemail.repositories.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository emailRepository;
    private final MailSenderService mailSenderService;
    private final ModelMapper mapper;

    public ResponseEntity<EmailDTO> sendEmail(EmailDTO emailDTO) {
        Email email = mapper.map(emailDTO, Email.class);
        email.setDate(LocalDate.now());
        email.setTime(LocalTime.now());
        try {
            mailSenderService.sendMail(emailDTO);
            email.setStatus(StatusEmail.SENT);
        } catch (Exception e) {
            email.setStatus(StatusEmail.ERROR);
        }
        emailRepository.save(email);
        return ResponseEntity.ok(mapper.map(email, EmailDTO.class));
    }

    public ResponseEntity<List<EmailDTO>> findAll() {
        List<Email> emails = emailRepository.findAll();
        if (emails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<EmailDTO> dtos = new ArrayList<>();
        emails.forEach(email -> {
            EmailDTO emailDTO = mapper.map(email, EmailDTO.class);
            dtos.add(emailDTO);
        });
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<EmailDTO> findById(UUID id) {
        Optional<Email> email = emailRepository.findById(id);
        if (email.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.map(email.get(), EmailDTO.class));
    }
}
