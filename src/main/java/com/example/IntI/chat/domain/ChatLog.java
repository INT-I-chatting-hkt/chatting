package com.example.IntI.chat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class ChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long roomId;
    private String userId;
    private String nickName;
    private String message;
    private String profileURL;
    public ChatLog(){

    }
    public ChatLog(Long roomId, String userId, String nickName, String message,String profileURL){
        this.roomId=roomId;
        this.userId=userId;
        this.nickName=nickName;
        this.message=message;
        this.profileURL=profileURL;
    }
}
