package com.app.milestone.repository;

import com.app.milestone.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SchoolRepository extends JpaRepository<School, Long>, SchoolCustomRepository {
}
