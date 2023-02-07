package com.example.IntI.chatroom.service;

import com.example.IntI.chatroom.repository.ChatRoomRepository;
import com.example.IntI.domain.ChattingRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChattingRoom> getAllRooms() {
        return chatRoomRepository.findAllRooms();
    }

}
