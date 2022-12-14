package com.app.milestone.service;

import com.app.milestone.domain.AlarmDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.User;
import com.app.milestone.repository.AlarmRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final UserRepository userRepository;
    private final PeopleRepository peopleRepository;
    private final AlarmRepository alarmRepository;

    //    안읽은 알람
    private List<AlarmDTO> showNoneCheckAlaram(Long userId) {
        String type = "";
        if (peopleRepository.findById(userId).isPresent()) {
            type = "people";
        } else {
            type = "school";
        }
        return alarmRepository.findNoneCheckAlarmByType(userId, type);
    }
}
