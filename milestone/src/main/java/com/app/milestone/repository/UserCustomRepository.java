package com.app.milestone.repository;

import com.app.milestone.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserCustomRepository {
    public List<User> findByCreatedDate (Pageable pageable);

    public User findByUserId(Long userId);

    public Optional<User> findByUserEmail(String userEmail);

    public List<User> findAll();

    public void clearStore();
}
