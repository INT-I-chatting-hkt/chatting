package com.example.IntI.chat.domain;

import lombok.Data;

@Data
public class ChatMessageDto {
    private Long roomId;
    private String userId;
    private String nickName;
    private String message;
    private String messageType;
    private String profileURL;
}
