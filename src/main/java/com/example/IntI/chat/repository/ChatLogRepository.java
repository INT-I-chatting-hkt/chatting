package com.example.IntI.chat.repository;

import com.example.IntI.chat.domain.ChatLog;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatLogRepository {
    private final EntityManager em;
    public void insert(ChatLog chatLog){
        em.persist(chatLog);
    }
    public List<ChatLog> findAllByRoomId(Long roomId){
        return em.createQuery("select cl from ChatLog cl where cl.roomId =: roomId", ChatLog.class)
                .setParameter("roomId",roomId).getResultList();
    }
}
