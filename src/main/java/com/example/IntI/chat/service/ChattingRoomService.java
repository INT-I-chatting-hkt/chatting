package com.example.IntI.chat.service;

import com.example.IntI.chat.repository.ChattingRoomRepository;
import com.example.IntI.domain.ChattingRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChattingRoomService {
    private final ChattingRoomRepository chattingRoomRepository;
    @Transactional
    public void join(ChattingRoom chattingRoom){
        chattingRoomRepository.join(chattingRoom);
    }

    public ChattingRoom findOne(Long id){
        return chattingRoomRepository.findRoomById(id);
    }
}
