package com.example.IntI.domain;

import com.example.IntI.chat.domain.Question;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime createTime;
    private String context;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Answer() {
    }

    public Answer(Question question, User user, String context) {
        this.context = context;
        this.question = question;
        this.user = user;
        this.createTime = LocalDateTime.now();
    }
}
