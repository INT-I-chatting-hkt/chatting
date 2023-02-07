package com.example.IntI.domain;

import com.example.IntI.chat.domain.ChatRoomDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Getter
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String profileUrl;
    protected ChattingRoom(String name,String description,String profileUrl){
        this.name=name;
        this.description=description;
        this.profileUrl=profileUrl;
    }
    protected ChattingRoom(){

    }
    public static ChattingRoom create(String name,String description,String profileUrl){
        ChattingRoom chattingRoom = new ChattingRoom(name,description,profileUrl);
        return chattingRoom;
    }

    public void setProfile(String profilePath) {
        this.profileUrl = profilePath;
    }
}
