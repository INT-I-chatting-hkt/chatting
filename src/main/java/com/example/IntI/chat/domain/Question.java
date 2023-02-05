package com.example.IntI.chat.domain;

import com.example.IntI.domain.ChattingRoom;
import com.example.IntI.domain.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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
}
