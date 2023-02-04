package com.example.IntI.repository;
import com.example.IntI.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public Long join(User user){
        em.persist(user);
        return user.getId();
    }

}
