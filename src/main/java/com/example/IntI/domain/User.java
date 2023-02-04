package com.example.IntI.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;
    private String profileUrl;

    protected User(String nickname){
        this.nickname=nickname;
        this.profileUrl="Empty";
    }
    protected User(){

    }
    public static User createUser(String nickname){
        User createUser = new User(nickname);
        return createUser;
    }
}
