package com.app.milestone.repository;

import com.app.milestone.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface SchoolRepository extends JpaRepository<School, Long>, SchoolCustomRepository {
    public Long countBy();
    Optional<School> findByUserEmail(String userEmail);

    @Modifying(clearAutomatically = true)
    public void deleteByUserPassword(String password);
}
