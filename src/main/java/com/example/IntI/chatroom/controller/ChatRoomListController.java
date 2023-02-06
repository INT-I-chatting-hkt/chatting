package com.example.IntI.chatroom.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/chat")
public class ChatRoomListController {

    @GetMapping
    public String chatList(Model model){

        return "/chat-list";
    }
}
