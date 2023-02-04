package com.example.IntI.chat.controller;

import com.example.IntI.chat.domain.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log4j2
public class StompChatController {
    private final SimpMessagingTemplate template;

    /**
     * pub Endpoint
     * @param message
     */
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDto message){
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room" + message.getRoomId(),message);
    }
    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDto message){
        log.info("# user name : "+message.getWriter());
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
