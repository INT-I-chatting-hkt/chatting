package com.example.IntI.chat.domain;

import lombok.Data;

@Data
public class ChatMessageDto {
    private String roomId;
    private String writer;
    private String message;
    private MessageType messageType;
}
