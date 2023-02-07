package com.example.IntI.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;
    private String profileUrl;

    private String userId;
    private String userPassword;

    @OneToMany
    private List<Answer> answer;

    protected User(String userId,String userPassword,String nickname){
        this.userId=userId;
        this.userPassword=userPassword;
        this.nickname=nickname;
        this.profileUrl="Empty";
    }
    protected User(){

    }
    public static User createUser(String userId,String userPassword,String nickname){
        User createUser = new User(userId,userPassword,nickname);
        return createUser;
    }

    public boolean validate(String userId,String userPassword){
        return (Objects.equals(this.userId, userId) && Objects.equals(this.userPassword,userPassword));
    }
}
