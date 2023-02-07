package com.example.IntI.qna.repository;

import com.example.IntI.chat.domain.Question;
import com.example.IntI.domain.Answer;
import com.example.IntI.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.example.IntI.domain.Status.ADOPT;
import static com.example.IntI.domain.Status.NOT_ADOPT;

@Repository
@RequiredArgsConstructor
public class QnaRepository {

    private final EntityManager em;

    public List<Question> findAllQuestions(Long roomId) {
        return em.createQuery("select q from Question q where q.chattingRoom.id=:roomId", Question.class)
                .setParameter("roomId", roomId).getResultList();
    }

    public Question findQuestion(Long questionId) {
        return em.find(Question.class, questionId);
    }

    public List<Answer> findAllAnswers(Long questionId) {
        return em.createQuery("select a from Answer a where a.question.id=:questionId").setParameter("questionId", questionId).getResultList();
    }

    public Answer findAnswer(Long answerId) {
        return em.find(Answer.class, answerId);
    }

    public List<Answer> findNotAdoptedAnswers(Long questionId) {
        return em.createQuery("select a from Answer a where a.question.id=:questionId and a.status=:status", Answer.class)
                .setParameter("questionId", questionId).setParameter("status", NOT_ADOPT).getResultList();
    }

    public Answer findAdoptAnswer(Long questionId) {
        List<Answer> answerList = em.createQuery("select a from Answer a where a.question.id=:questionId and a.status=:status", Answer.class)
                .setParameter("questionId", questionId).setParameter("status", ADOPT).getResultList();

        if(answerList.isEmpty()) {
            return null;
        } else {
            return answerList.get(0);
        }
    }

    public Answer writeAnswer(Question question, User writer, String context) {
        Answer answer = new Answer(question, writer, context);
        em.persist(answer);
        return answer;
    }

    public Answer adopt(Answer answer) {
        answer.updateStatus(ADOPT);
        return answer;
    }
}
