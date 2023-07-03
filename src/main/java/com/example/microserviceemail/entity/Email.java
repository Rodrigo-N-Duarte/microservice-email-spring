package com.example.microserviceemail.entity;

import com.example.microserviceemail.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_email")
@Data
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;
    @Column
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column
    private String owner;
    @Column
    private String addressee;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR")
    private StatusEmail status;
}
