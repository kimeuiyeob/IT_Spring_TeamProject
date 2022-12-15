package com.app.milestone.aspect;

import com.app.milestone.domain.AlarmDTO;
import com.app.milestone.domain.ServiceDTO;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.entity.*;
import com.app.milestone.entity.Alarm;
import com.app.milestone.repository.AlarmRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import com.app.milestone.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
@RequiredArgsConstructor
@Slf4j
public class AlarmAspect {
    private final AlarmRepository alarmRepository;
    private final SchoolRepository schoolRepository;
    private final PeopleRepository peopleRepository;


    // 조인포인트 : 메소드가 실행되기 전 시점을 저장하는 곳
//    .getArgs : 메소드실행시 인자 가져옴
//    public void saveAlarm(ProceedingJoinPoint proceedingJoinPoint){
//        log.info("====================================");
//        log.info("pointCut: " + proceedingJoinPoint.getArgs());
//        log.info("====================================");
////        alarmRepository.
//    }
    @After("@annotation(com.app.milestone.aspect.Alarm)")
    public void saveAlarm(JoinPoint joinPoint) {
//        ServiceDTO serviceDTO = Arrays.stream(joinPoint.getArgs()).filter()
        AlarmDTO alarmDTO = new AlarmDTO();
        log.info("====================================");
        log.info("pointCut: " + joinPoint.getArgs()[0]);
        log.info("pointCut: " + joinPoint.getArgs()[0]);
        log.info("pointCut: " + joinPoint.getArgs()[0]);
        log.info("pointCut: " + Money.class.isInstance(joinPoint.getArgs()[0]));
        log.info("pointCut: " + Service.class.isInstance(joinPoint.getArgs()[0]));
        log.info("pointCut: " + TalentDTO.class.isInstance(joinPoint.getArgs()[0]));
        log.info("====================================");

        if (Money.class.isInstance(joinPoint.getArgs()[0])) {
            alarmDTO = new AlarmDTO();
            Money money = Money.class.cast(joinPoint.getArgs()[0]);
            alarmDTO.setReceiver("school");
            alarmDTO.setItem(money.getMoneyCash() + "");
            alarmDTO.setType("기부금");
            alarmDTO.setCheckAlarm(false);
            Alarm alarm = alarmRepository.save(alarmDTO.toEntity());
            alarm.changeGiver(money.getPeople());
            alarm.changeTaker(money.getSchool());
        }
        if (Service.class.isInstance(joinPoint.getArgs()[0])) {
            alarmDTO = new AlarmDTO();
            Service service = Service.class.cast(joinPoint.getArgs()[0]);
            String visitDate = service.getServiceVisitDate() + "";
            alarmDTO.setReceiver("school");
            alarmDTO.setItem(visitDate.substring(0,visitDate.indexOf('T')));
            alarmDTO.setType("방문일");
            alarmDTO.setCheckAlarm(false);
            Alarm alarm = alarmRepository.save(alarmDTO.toEntity());
            alarm.changeGiver(service.getPeople());
            alarm.changeTaker(service.getSchool());
        }
        if (TalentDTO.class.isInstance(joinPoint.getArgs()[0])) {
            alarmDTO = new AlarmDTO();
            TalentDTO talentDTO = TalentDTO.class.cast(joinPoint.getArgs()[0]);
            String ableDate = talentDTO.getTalentAbleDate() + "";
            log.info("============================"+ ableDate);
            alarmDTO.setReceiver("people");
            alarmDTO.setItem(ableDate.substring(0,ableDate.indexOf('T')));
            alarmDTO.setType("재능기부일");
            alarmDTO.setCheckAlarm(false);
            Alarm alarm = alarmRepository.save(alarmDTO.toEntity());
            alarm.changeGiver(schoolRepository.findById(talentDTO.getSchoolUserId()).get());
            alarm.changeTaker(peopleRepository.findById(talentDTO.getPeopleUserId()).get());
        }
//        alarmRepository.
    }

//    매개변수를 어떤순서로 어떻게 받게 했는지 모르기 때문에
//    CommunityReplyDTO replyDTO = Arrays.stream(joinPoint.getArgs())
//    filter로 찾으려는 객체만 뽑아오고
//            .filter(CommunityReplyDTO.class::isInstance)
//    업캐스팅후
//            .map(CommunityReplyDTO.class::cast)
//    첫번째꺼 가져온다.
//            .findFirst()
//    만일 filter에서 못찾아서 오류 떳을 때(아마 NPE) 오류잡는다.
//            .orElseThrow(() -> new IllegalArgumentException("User를 찾을 수 없습니다."));
}
