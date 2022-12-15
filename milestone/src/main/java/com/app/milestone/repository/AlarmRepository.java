package com.app.milestone.repository;

import com.app.milestone.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmCustomRepository {
}
