package com.example.IntI.domain;

import lombok.Data;

@Data
public class UserCreateForm {
    private String userId;
    private String userPassword;
    private String nickName;
}
