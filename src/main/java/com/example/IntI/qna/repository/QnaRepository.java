package com.example.IntI.qna.repository;

import com.example.IntI.chat.domain.Question;
import com.example.IntI.domain.Answer;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.example.IntI.domain.Status.ADOPT;

@Repository
@RequiredArgsConstructor
public class QnaRepository {

    private final EntityManager em;

    public List<Question> findAllQuestions() {
        return em.createQuery("select q from Question q", Question.class)
                .getResultList();
    }

    public Question findQuestion(Long questionId) {
        return em.find(Question.class, questionId);
    }

    public Answer findMainAnswer(Long questionId) {
        List<Answer> answerList = em.createQuery("select a from Answer a where a.question.id=:questionId and a.status=:aaa",
                        Answer.class)
                .setParameter("questionId", questionId).setParameter("aaa", ADOPT).getResultList();

        if(answerList.isEmpty()) {
            return null;
        } else {
            return answerList.get(0);
        }
    }
}
