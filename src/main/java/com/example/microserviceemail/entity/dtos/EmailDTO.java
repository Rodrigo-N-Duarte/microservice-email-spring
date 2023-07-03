package com.example.microserviceemail.entity.dtos;

import com.example.microserviceemail.enums.StatusEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID id;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
    @NotBlank
    private String owner;
    @NotBlank
    private String addressee;
    private LocalDate date;
    private LocalTime time;
    private StatusEmail status;
}
