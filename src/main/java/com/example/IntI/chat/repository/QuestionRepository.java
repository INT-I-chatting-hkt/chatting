package com.example.IntI.chat.repository;

import com.example.IntI.chat.domain.Question;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {
    private final EntityManager em;

    public void join(Question question){
        em.persist(question);
    }
}
