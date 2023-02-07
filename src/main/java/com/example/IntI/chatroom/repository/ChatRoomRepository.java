package com.example.IntI.chatroom.repository;

import com.example.IntI.domain.ChattingRoom;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {

    private final EntityManager em;
    public List<ChattingRoom> findAllRooms() {
        return em.createQuery("select cr from ChattingRoom cr", ChattingRoom.class)
                .getResultList();
    }
}
