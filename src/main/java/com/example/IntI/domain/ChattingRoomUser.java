package com.example.IntI.domain;

import jakarta.persistence.*;

@Entity
public class ChattingRoomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChattingRoom chattingRoom;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
