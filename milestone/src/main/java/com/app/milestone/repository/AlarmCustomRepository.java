package com.app.milestone.repository;

import com.app.milestone.domain.AlarmDTO;
import com.app.milestone.entity.Alarm;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AlarmCustomRepository {
    public List<AlarmDTO> findAlarmByType(Long userId, Pageable pageable, String type);
    public List<AlarmDTO> findAlarmByType1(Long userId, Pageable pageable, String type);
    public List<AlarmDTO> findAlarmByType2(Long userId, Pageable pageable, String type);
    public List<AlarmDTO> findNoneCheckAlarmByType(Long userId, String type);
    public Long countAlarm(Pageable pageable, Long userId);
}
