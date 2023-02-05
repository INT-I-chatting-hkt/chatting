package com.example.IntI.chat.domain;

import lombok.Data;

@Data
public class ChatMessageDto {
    private Long roomId;
    private Long userId;
    private String message;
    private MessageType messageType;
}
