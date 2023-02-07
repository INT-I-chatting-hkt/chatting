package com.example.IntI.qna.service;

import com.example.IntI.chat.domain.Question;
import com.example.IntI.domain.Answer;
import com.example.IntI.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

    public List<Question> getAllQuestions() {
        return qnaRepository.findAllQuestions();
    }

    public Question getQuestion(Long questionId) {
        return qnaRepository.findQuestion(questionId);
    }

    public Answer getAdoptAnswer(Long questionId) {
        return qnaRepository.findMainAnswer(questionId);
    }
}
