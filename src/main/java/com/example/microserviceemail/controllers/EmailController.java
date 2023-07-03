package com.example.microserviceemail.controllers;

import com.example.microserviceemail.entity.dtos.EmailDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.microserviceemail.services.EmailService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @PostMapping("/send")
    public ResponseEntity<EmailDTO> sendEmail(@Valid @RequestBody EmailDTO emailDTO) {
        return emailService.sendEmail(emailDTO);
    }

    @GetMapping
    public ResponseEntity<List<EmailDTO>> findAll() {
        return emailService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailDTO> findById(@Valid @PathVariable("id") UUID id) {
        return emailService.findById(id);
    }
}
