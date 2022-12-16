package com.app.milestone.repository;

import com.app.milestone.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

@Component
public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmCustomRepository {
    public void deleteByGiverUserId(Long userId);
    public void deleteByTakerUserId(Long userId);
}
