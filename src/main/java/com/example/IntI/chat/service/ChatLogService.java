package com.example.IntI.chat.service;

import com.example.IntI.chat.domain.ChatLog;
import com.example.IntI.chat.repository.ChatLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatLogService {
    private final ChatLogRepository chatLogRepository;

    @Transactional
    public void insert(ChatLog chatLog){
        chatLogRepository.insert(chatLog);
    }

    public List<ChatLog> findAllByRoomId(Long roomId){
        return chatLogRepository.findAllByRoomId(roomId);
    }

}
