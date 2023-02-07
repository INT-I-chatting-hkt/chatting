package com.example.IntI.chat.controller;

import com.example.IntI.chat.domain.ChatLog;
import com.example.IntI.chat.domain.ChatMessageDto;
import com.example.IntI.chat.domain.MessageType;
import com.example.IntI.chat.domain.Question;
import com.example.IntI.chat.repository.ChatLogRepository;
import com.example.IntI.chat.service.ChatLogService;
import com.example.IntI.chat.service.ChattingRoomService;
import com.example.IntI.chat.service.QuestionService;
import com.example.IntI.service.UserService;
import com.example.IntI.domain.ChattingRoom;
import com.example.IntI.domain.User;
import jakarta.servlet.http.HttpServletRequest;
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
    private final QuestionService questionService;
    private final ChattingRoomService chattingRoomService;
    private final UserService userService;
    private final ChatLogService chatLogService;

    /**
     * pub Endpoint
     * @param message
     */
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDto message){
        message.setMessage(message.getUserId() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room" + message.getRoomId(),message);
    }
    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDto message){
        if(message.getMessageType().equals("Question")){
            ChattingRoom chattingRoom = chattingRoomService.findOne(message.getRoomId());
            User user = userService.findOneByUserId(message.getUserId());
            questionService.join(Question.create(message.getMessage(),user,chattingRoom));
        }
        chatLogService
                .insert(new ChatLog(message.getRoomId(),message.getUserId()
                        ,message.getNickName(), message.getMessage(), message.getProfileURL()));
        log.info("# user id : "+message.getUserId());
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
