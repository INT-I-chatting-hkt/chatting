package com.example.IntI.chat.controller;

import com.example.IntI.chat.domain.ChatLog;
import com.example.IntI.chat.domain.ChatRoomDto;
import com.example.IntI.chat.repository.ChattingRoomRepository;
import com.example.IntI.chat.service.ChatLogService;
import com.example.IntI.chat.service.ChattingRoomService;
import com.example.IntI.domain.ChattingRoom;
import com.example.IntI.domain.User;
import com.example.IntI.security.JwtTokenProvider;
import com.example.IntI.service.UserService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {

    private final ChattingRoomRepository chattingRoomRepository;
    private final ChattingRoomService chattingRoomService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final ChatLogService chatLogService;

    //채팅방 목록 조회
    @GetMapping(value = "/room/join")
    public String rooms(Model model){
        model.addAttribute("chatRoomDto",new ChatRoomDto());
        return "createRoom";
    }

    //채팅방 개설
    @PostMapping(value = "/room/join")
    public String create(@ModelAttribute ChatRoomDto chatRoomDto,
                         RedirectAttributes rttr){
        ChattingRoom chattingRoom = ChattingRoom.create(chatRoomDto.getName(),chatRoomDto.getDescription(),
                "null");
        log.info("# Create Chat Room , name: " + chattingRoom.getName());
        chattingRoomService.join(chattingRoom);
        rttr.addFlashAttribute("roomName", chattingRoom.getName());
        return "redirect:/chat";
    }
    //채팅방 조회
    @GetMapping("/room")
    public String getRoom(Long roomId, Model model,HttpServletRequest request){
        String token = jwtTokenProvider.resolveCookieToken(request,"inti-token");
        String userId = jwtTokenProvider.getValidateValue(token);
        User user = userService.findOneByUserId(userId);
        log.info("# get Chat Room, roomID : " + roomId);
        log.info("# find Room Id : "+ chattingRoomRepository.findRoomById(roomId));
        log.info("# user name : "+ user.getNickname());
        List<ChatLog> chatLogs = chatLogService.findAllByRoomId(roomId);
        model.addAttribute("chatLogs",chatLogs);
        model.addAttribute("room", chattingRoomRepository.findRoomById(roomId));
        model.addAttribute("user",user);
        return "room2";
    }
}
