package com.app.milestone.repository;

import com.app.milestone.entity.People;
import com.app.milestone.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserCustomRepository {
    public List<User> findByCreatedDate(Pageable pageable);

    public User findByUserId(Long userId);

    public Optional<User> findByUserEmail(String userEmail);

    public List<User> findAll();

    public void clearStore();

    public Long findByUserEmailAndUserPassword(String userEmail, String userPassword);

    public User OAuthIdFind(String password);

    /*황지수*/
//    public Long countByUserEmail(String userEmail);
    /*/황지수*/
}
