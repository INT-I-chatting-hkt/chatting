package com.example.IntI.service;

import com.example.IntI.domain.User;
import com.example.IntI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final List<String> profileImageList = new ArrayList<>(
            Arrays.asList("dekisugi.jpg", "doraemon.jpg", "dorami.jpg", "gou.jpg", "jyaiko.jpg", "nobita.jpg", "shizuka.jpg", "suneo.jpg"));

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public void join(User user) {
        List<User> userList = userRepository.findByUserIdList(user.getUserId());
        if (!userList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
        userRepository.join(user);
        setProfile(user);
    }

    public void setProfile(User user) {
        String dirPath = "/images/userImage";
        Collections.shuffle(profileImageList);
        user.setProfile(dirPath + "/" + profileImageList.get(0));
    }

    public User findOneByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
