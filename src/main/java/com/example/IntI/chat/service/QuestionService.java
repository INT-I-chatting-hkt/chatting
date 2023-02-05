package com.example.IntI.chat.service;

import com.example.IntI.chat.domain.Question;
import com.example.IntI.chat.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional
    public void join(Question question){
        questionRepository.join(question);
    }
}
