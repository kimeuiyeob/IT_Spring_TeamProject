package com.app.milestone.repository;

import com.app.milestone.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TalentRepository extends JpaRepository<Talent, Long> {

}
