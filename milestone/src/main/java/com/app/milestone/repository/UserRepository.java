package com.app.milestone.repository;

import com.app.milestone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    Optional<User> findByUserEmail(String userEmail);

}
