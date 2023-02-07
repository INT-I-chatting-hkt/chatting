package com.example.IntI.chatroom.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/chat")
public class ChatRoomListController {

    @GetMapping
    public String chatList(Model model){

        ChatRoom chatRoom1 = new ChatRoom(1, "포토샵", "포토샵 잘하시는 분 오세요");
        ChatRoom chatRoom2 = new ChatRoom(2, "알고리즘 스터디", "알고리즘 같이 풀어봐요~");

        List<ChatRoom> chatData = new ArrayList<>();
        chatData.add(chatRoom1);
        chatData.add(chatRoom2);
        chatData.add(chatRoom1);
        chatData.add(chatRoom2);
        model.addAttribute("chatList", chatData);

        return "/chat-list";
    }

    @Data
    static class ChatRoom {
        private int roomid;
        private String chatname;
        private String chatdescription;

        public ChatRoom(int roomid, String chatname, String chatdescription) {
            this.roomid = roomid;
            this.chatname = chatname;
            this.chatdescription = chatdescription;
        }
    }
}
