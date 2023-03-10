package com.example.IntI.chat.domain;

import com.example.IntI.domain.Answer;
import com.example.IntI.domain.ChattingRoom;
import com.example.IntI.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime createTime;
    private String context;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private ChattingRoom chattingRoom;

    @OneToOne(fetch = FetchType.LAZY)
    private Answer adoptedAnswer;

    @OneToMany(mappedBy = "question")
    private List<Answer> answerList = new ArrayList<>();

    protected Question(String context,User user,ChattingRoom chattingRoom){
        this.context=context;
        this.user=user;
        this.chattingRoom=chattingRoom;
        this.createTime=LocalDateTime.now();
    }
    protected Question(){

    }
    public static Question create(String context,User user,ChattingRoom chattingRoom){
        Question question = new Question(context,user,chattingRoom);
        return question;
    }

    public void adopt(Answer answer){
        this.adoptedAnswer=answer;
    }
}
