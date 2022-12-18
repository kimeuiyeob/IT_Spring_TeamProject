package com.app.milestone.service;

import com.app.milestone.domain.AlarmDTO;
import com.app.milestone.domain.PasswordDTO;
import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.entity.Alarm;
import com.app.milestone.repository.AlarmRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import com.app.milestone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
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
            } else {
                log.info("==========================asd==================" + alarmDTO.getGiverId());
                alarmDTO.setName(schoolRepository.findById(alarmDTO.getGiverId()).get().getSchoolName());
            }
            alarmDTO.setPhoneNumber(userRepository.findById(alarmDTO.getGiverId()).get().getUserPhoneNumber());
        }
        return alarmDTOList;
    }

    //    개인 알람 관리
    public Page<AlarmDTO> peopleAlarmList(Long userId, Integer page) {
        if (page == null) page = 0;
        String type = userRepository.findById(userId).get().getClass().getSimpleName().toLowerCase();
        log.info("알람 서비스에 들어온 sessionId : " + userId);

        Pageable pageable = PageRequest.of(page, 7);
        List<AlarmDTO> list = alarmRepository.findAlarmByType1(userId, pageable, type);
        for(AlarmDTO alarmDTO : list){
            alarmDTO.setName(schoolRepository.findById(alarmDTO.getGiverId()).get().getSchoolName());
            alarmDTO.setPhoneNumber(userRepository.findById(alarmDTO.getGiverId()).get().getUserPhoneNumber());
        }
        log.info("서비스에 들어온 list : " + list);

        Page<AlarmDTO> alarms = new PageImpl<>(list, pageable, Integer.valueOf("" + alarmRepository.countAlarm(pageable, userId)));
        return alarms;
    }

    //    개인 알람 관리
    public Page<AlarmDTO> schoolAlarmList(Long userId, Integer page) {
        if (page == null) page = 0;
        String type = userRepository.findById(userId).get().getClass().getSimpleName().toLowerCase();
        log.info("알람 서비스에 들어온 sessionId : " + userId);

        Pageable pageable = PageRequest.of(page, 7);
        List<AlarmDTO> list = alarmRepository.findAlarmByType2(userId, pageable, type);
        for(AlarmDTO alarmDTO : list){
            alarmDTO.setName(peopleRepository.findById(alarmDTO.getGiverId()).get().getPeopleNickname());
            alarmDTO.setPhoneNumber(userRepository.findById(alarmDTO.getGiverId()).get().getUserPhoneNumber());
        }
        log.info("서비스에 들어온 list : " + list);

        Page<AlarmDTO> alarms = new PageImpl<>(list, pageable, Integer.valueOf("" + alarmRepository.countAlarm(pageable, userId)));
        return alarms;
    }

    //  alarmId로 확인하기
    public void updateAlarmCheck(Long alarmId) {
        log.info("===========알람 서비스 업데이트============");
        alarmRepository.updateByAlarmId(alarmId);
    }
}
