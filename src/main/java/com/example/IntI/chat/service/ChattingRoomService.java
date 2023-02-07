package com.example.IntI.chat.service;

import com.example.IntI.chat.repository.ChattingRoomRepository;
import com.example.IntI.domain.ChattingRoom;
import com.example.IntI.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChattingRoomService {
    private final ChattingRoomRepository chattingRoomRepository;
    private final List<String> roomImageList = new ArrayList<>(new ArrayList<>(
            Arrays.asList("amazon.png", "apple.jpg", "google.jpg", "microsoft.png","samsung.png", "tesla.png")));

    @Transactional
    public void join(ChattingRoom chattingRoom){
        chattingRoomRepository.join(chattingRoom);
        setProfile(chattingRoom);
    }

    public void setProfile(ChattingRoom chattingRoom) {
        String dirPath = "/images/roomImage";
        Collections.shuffle(roomImageList);
        chattingRoom.setProfile(dirPath + "/" + roomImageList.get(0));
    }

    public ChattingRoom findOne(Long id){
        return chattingRoomRepository.findRoomById(id);
    }
}
