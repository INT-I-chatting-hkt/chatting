package com.example.IntI.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime createTime;
    private String context;
    @Enumerated(value = EnumType.STRING)
    private Status status;
}
