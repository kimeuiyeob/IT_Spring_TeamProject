package com.app.milestone.repository;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface PeopleRepository extends JpaRepository<People, Long>, PeopleCustomRepository {

    Optional<People> findByUserEmail(String userEmail);
}
