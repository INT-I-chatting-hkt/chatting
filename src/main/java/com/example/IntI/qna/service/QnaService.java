package com.example.IntI.qna.service;

import com.example.IntI.chat.domain.Question;
import com.example.IntI.domain.Answer;
import com.example.IntI.domain.User;
import com.example.IntI.qna.repository.QnaRepository;
import com.example.IntI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;
    private final UserService userService;

    public List<Question> getAllQuestions(Long roomId) {
        return qnaRepository.findAllQuestions(roomId);
    }
    public List<Question> getAllQuestionsWithAdoptedAnswer(Long roomId){
        return qnaRepository.findAllWithAdoptedAnswer(roomId);
    }

    public Question getQuestion(Long questionId) {
        return qnaRepository.findQuestion(questionId);
    }

    public List<Answer> getAllAnswers(Long questionId) {
        return qnaRepository.findAllAnswers(questionId);
    }

    public Answer getAnswer(Long answerId) {
        return qnaRepository.findAnswer(answerId);
    }

    public List<Answer> getNotAdoptedAnswers(Long questionId, Long answerId) {
        return qnaRepository.findNotAdoptedAnswers(questionId,answerId);
    }

    public Answer getAdoptAnswer(Long questionId) {
        return qnaRepository.findAdoptAnswer(questionId);
    }

    public void adoptAnswer(Long answerId,Long questionId){
        Question question = qnaRepository.findQuestion(questionId);
        Answer answer = qnaRepository.findAnswer(answerId);
        question.adopt(answer);
        return;
    }

    public Long createAnswer(String userId,String context,Long questionId){
        User user = userService.findOneByUserId(userId);
        Question question = qnaRepository.findQuestion(questionId);
        Answer answer = new Answer(user,question,context);
        return qnaRepository.createAnswer(answer);
    }
}
