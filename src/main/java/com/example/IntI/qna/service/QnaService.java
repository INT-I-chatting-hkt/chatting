package com.example.IntI.qna.service;

import com.example.IntI.chat.domain.Question;
import com.example.IntI.domain.Answer;
import com.example.IntI.domain.User;
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

    public List<Answer> getAllAnswers(Long questionId) {
        return qnaRepository.findAllAnswers(questionId);
    }

    public Answer getAnswer(Long answerId) {
        return qnaRepository.findAnswer(answerId);
    }

    public List<Answer> getNotAdoptedAnswers(Long questionId) {
        return qnaRepository.findNotAdoptedAnswers(questionId);
    }

    public Answer getAdoptAnswer(Long questionId) {
        return qnaRepository.findAdoptAnswer(questionId);
    }

    public Answer addAnswer(Long questionId, User writer, String context) {
        Question question = getQuestion(questionId);
        return qnaRepository.writeAnswer(question, writer, context);
    }

    public Answer adoptAnswer(Long answerId) {
        Answer answer = getAnswer(answerId);
        Question question = answer.getQuestion();

        if(getAdoptAnswer(question.getId()) == null) {
            qnaRepository.adopt(answer);
            return answer;
        }

        return null;
    }
}
