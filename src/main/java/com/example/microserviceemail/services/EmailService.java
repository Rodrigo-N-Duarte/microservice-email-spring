package com.example.microserviceemail.services;

import com.example.microserviceemail.entity.Email;
import com.example.microserviceemail.entity.dtos.EmailDTO;
import com.example.microserviceemail.enums.StatusEmail;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.microserviceemail.repositories.EmailRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository emailRepository;
    private final MailSenderService mailSenderService;
    private final ModelMapper mapper = new ModelMapper();
    public ResponseEntity<EmailDTO> sendEmail(EmailDTO emailDTO) {
        Email email = mapper.map(emailDTO, Email.class);
        try {
            mailSenderService.sendMail(emailDTO);
            email.setStatus(StatusEmail.SENT);
        }
        catch (Exception e) {
            email.setStatus(StatusEmail.ERROR);
        }
        emailRepository.save(email);
        return ResponseEntity.ok(mapper.map(email, EmailDTO.class));
    }

    public ResponseEntity<List<EmailDTO>> findAll() {
        List<Email> emails = emailRepository.findAll();
        List<EmailDTO> dtos = new ArrayList<>();
        emails.forEach(email -> {
            EmailDTO emailDTO = mapper.map(email, EmailDTO.class);
            dtos.add(emailDTO);
        });
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<EmailDTO> findById(UUID id) {
        try {
            Email email = emailRepository.findById(id).get();
            return ResponseEntity.ok(mapper.map(email, EmailDTO.class));
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
