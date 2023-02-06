package com.example.IntI.chat.repository;

import com.example.IntI.chat.domain.Question;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {
    private final EntityManager em;

    public void join(Question question){
        em.persist(question);
    }

    public List<Question> findAll(Long roomId){
        return em.createQuery("select q from Question q join q.chattingRoom cr" +
                " where cr.id =: roomId", Question.class).setParameter("roomId",roomId).getResultList();
    }
}
