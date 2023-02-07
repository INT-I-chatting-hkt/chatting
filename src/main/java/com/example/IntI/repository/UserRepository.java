package com.example.IntI.repository;
import com.example.IntI.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public Long join(User user){
        em.persist(user);
        return user.getId();
    }
    public User findOne(Long id){
        return em.find(User.class,id);
    }

    public List<User> findByUserIdList(String userId){
        return em.createQuery("select u from User u where u.userId =: userid", User.class)
                .setParameter("userid",userId).getResultList();
    }

    public User findByUserId(String userId){
        return em.createQuery("select u from User u where u.userId =: userid", User.class)
                .setParameter("userid",userId).getSingleResult();
    }
}
