package com.example.IntI.service;

import com.example.IntI.domain.User;
import com.example.IntI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findOne(Long id){
        return userRepository.findOne(id);
    }


    @Transactional
    public void join(User user){
        List<User> userList = userRepository.findByUserIdList(user.getUserId());
        if(!userList.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
        userRepository.join(user);
    }

    public User findOneByUserId(String userId){
        return userRepository.findByUserId(userId);
    }
}
