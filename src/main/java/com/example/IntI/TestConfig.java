package com.example.IntI;


import com.example.IntI.domain.User;
import com.example.IntI.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.metamodel.internal.MemberResolver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TestConfig {

    private final InitService initService;

    @PostConstruct
    public void Init(){
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    public class InitService{
        private final UserRepository userRepository;

        private final EntityManager em;

        public void dbInit1(){
            String nickname = "johan";
            userRepository.join(User.createUser(nickname));
            return;
        }

    };

}
