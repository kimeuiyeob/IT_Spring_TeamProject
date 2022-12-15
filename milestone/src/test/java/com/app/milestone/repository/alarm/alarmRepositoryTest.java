package com.app.milestone.repository.alarm;

import com.app.milestone.domain.AlarmDTO;
import com.app.milestone.entity.Alarm;
import com.app.milestone.entity.People;
import com.app.milestone.entity.User;
import com.app.milestone.repository.AlarmRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class alarmRepositoryTest {
    @Autowired
    private AlarmRepository alarmRepository;
    @Autowired
    private UserRepository userRepository;

    //    기부금 알람 추가
    @Test
    public void saveMoneyTest() {
        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setReceiver("school");
        alarmDTO.setType("기부금");
        alarmDTO.setItem("500000");
        alarmDTO.setCheckAlarm(false);
        Alarm alarm = alarmRepository.save(alarmDTO.toEntity());
        User giver = userRepository.findById(2L).get();
        User taker = userRepository.findById(105L).get();

        alarm.changeGiver(giver);
        alarm.changeTaker(taker);
    }

    //    기부금 알람 샘플데이터
    @Test
    public void saveMoneySample() {
        for (int i = 0; i < 23; i++) {
            AlarmDTO alarmDTO = new AlarmDTO();
            alarmDTO.setReceiver("school");
            alarmDTO.setType("기부금");
            alarmDTO.setItem(i+"50000");
            alarmDTO.setCheckAlarm(i%2 == 0 ? false : true);
            Alarm alarm = alarmRepository.save(alarmDTO.toEntity());
            User giver = userRepository.findById(2L+i).get();
            User taker = userRepository.findById(105L).get();

            alarm.changeGiver(giver);
            alarm.changeTaker(taker);
        }
    }
    
//    전체 알람
    @Test
    public void findSchoolAlarmByTypeTest(){
        String type = "school";
        Pageable pageable = PageRequest.of(0,10);
        log.info("====================================================================");
        log.info("========================"+alarmRepository.findAlarmByType(105L, pageable, type).size());
        log.info("====================================================================");
    }

//    안읽은 알람
    @Test
    public void findSchoolCheckAlarmByTypeTest(){
        String type = "school";
        log.info("====================================================================");
        log.info("========================"+alarmRepository.findNoneCheckAlarmByType(105L, type).size());
        log.info("====================================================================");
    }
}
