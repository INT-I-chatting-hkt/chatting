package com.example.IntI.chat.repository;

import com.example.IntI.chat.domain.ChatRoomDto;
import com.example.IntI.domain.ChattingRoom;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ChattingRoomRepository {
    private final EntityManager em;

    public List<ChattingRoom> findAllRooms(){
        return em.createQuery("select cr from ChattingRoom cr", ChattingRoom.class)
                .getResultList();
    }

    public ChattingRoom findRoomById(String id){
        return em.find(ChattingRoom.class,id);
    }

    public void join(ChattingRoom chattingRoom){
       em.persist(chattingRoom);
    }
}
