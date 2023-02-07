package com.example.IntI.chatroom.controller;

import com.example.IntI.chatroom.service.ChatRoomService;
import com.example.IntI.domain.ChattingRoom;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/chat")
@RequiredArgsConstructor
public class ChatRoomListController {

    private final ChatRoomService chatRoomService;

    @GetMapping
        public String chatList(Model model){

        List<ChattingRoom> chatData = chatRoomService.getAllRooms();
        model.addAttribute("chatList", chatData);

        return "chat-list";
    }
}
