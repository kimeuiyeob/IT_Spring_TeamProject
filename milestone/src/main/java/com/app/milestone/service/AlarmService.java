package com.app.milestone.service;

import com.app.milestone.domain.AlarmDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.User;
import com.app.milestone.repository.AlarmRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import com.app.milestone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmService {
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final PeopleRepository peopleRepository;
    private final AlarmRepository alarmRepository;

    //    안읽은 알람
    public List<AlarmDTO> showNoneCheckAlaram(Long userId) {
//        String type = "";
        String type = userRepository.findById(userId).get().getClass().getSimpleName().toLowerCase();
        List<AlarmDTO> alarmDTOList = alarmRepository.findNoneCheckAlarmByType(userId, type);
        for (AlarmDTO alarmDTO : alarmDTOList) {
            if (!type.equals("people")) {
                alarmDTO.setName(peopleRepository.findById(alarmDTO.getGiverId()).get().getPeopleNickname());
            }else{
                log.info("==========================asd=================="+alarmDTO.getGiverId());
                alarmDTO.setName(schoolRepository.findById(alarmDTO.getGiverId()).get().getSchoolName());
            }
            alarmDTO.setPhoneNumber(userRepository.findById(alarmDTO.getGiverId()).get().getUserPhoneNumber());
        }
        return alarmDTOList;
    }
}
