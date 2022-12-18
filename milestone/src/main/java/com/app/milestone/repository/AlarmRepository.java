package com.app.milestone.repository;

import com.app.milestone.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmCustomRepository {
    public void deleteByGiverUserId(Long userId);
    public void deleteByTakerUserId(Long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Alarm a set a.checkAlarm = true where a.alarmId = :alarmId")
    void updateByAlarmId(Long alarmId);
}
