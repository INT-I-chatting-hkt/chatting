package com.example.IntI.chat.controller;

import com.example.IntI.chat.domain.ChatRoomDto;
import com.example.IntI.chat.repository.ChattingRoomRepository;
import com.example.IntI.domain.ChattingRoom;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {

    private final ChattingRoomRepository repository;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public String rooms(Model model){
        log.info("# All Chat Rooms");
        model.addAttribute("list",repository.findAllRooms());
        return "rooms";
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    @ResponseBody
    public ChatRoomDto create(@ModelAttribute ChatRoomDto chatRoomDto,
                         RedirectAttributes rttr){
        ChattingRoom chattingRoom = ChattingRoom.create(chatRoomDto.getName(),chatRoomDto.getDescription(),
                "null");
        log.info("# Create Chat Room , name: " + chattingRoom.getName());
        repository.join(chattingRoom);
        return chatRoomDto;
        /*
        rttr.addFlashAttribute("roomName", chattingRoom.getName());
        return "redirect:/chat/rooms";

         */
    }
    //채팅방 조회
    @GetMapping("/room")
    public String getRoom(String roomId, Model model){

        log.info("# get Chat Room, roomID : " + roomId);
        log.info("# find Room Id : "+ repository.findRoomById(roomId));
        model.addAttribute("room", repository.findRoomById(roomId));
        return "room";
    }
}
