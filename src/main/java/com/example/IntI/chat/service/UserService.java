package com.example.IntI.chat.service;

import com.example.IntI.domain.User;
import com.example.IntI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findOne(Long id){
        return userRepository.findOne(id);
    }
}
