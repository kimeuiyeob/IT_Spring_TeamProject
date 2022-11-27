package com.app.milestone.repository;

import com.app.milestone.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PeopleRepository extends JpaRepository<People, Long> {

}
