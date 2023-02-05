package com.example.IntI.chat.domain;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class ChatRoomDto {
    private String name;
    private String description;

    public static ChatRoomDto create(String name,String description){
        ChatRoomDto room = new ChatRoomDto();
        room.name = name;
        room.description = description;
        return room;
    }
}
