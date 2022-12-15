package com.app.milestone.repository;

import com.app.milestone.domain.AlarmDTO;
import com.app.milestone.domain.QAlarmDTO;
import com.app.milestone.entity.QAlarm;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.milestone.entity.QAlarm.*;

@Repository
@RequiredArgsConstructor
public class AlarmCustomRepositoryImpl implements AlarmCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    //    전체 알람
    @Override
    public List<AlarmDTO> findAlarmByType(Long userId, Pageable pageable, String type) {
        return jpaQueryFactory.select(new QAlarmDTO(
                alarm.alarmId,
                alarm.receiver,
                alarm.type,
                alarm.item,
                alarm.checkAlarm,
                alarm.giver.userId,
                alarm.taker.userId
        )).from(alarm)
                .where(
                        alarm.taker.userId.eq(userId),
                        checkUserType(type)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(alarm.createdDate.desc())
                .fetch();
    }

    //    안읽은 알람 전체
    @Override
    public List<AlarmDTO> findNoneCheckAlarmByType(Long userId, String type) {
        return jpaQueryFactory.select(new QAlarmDTO(
                alarm.alarmId,
                alarm.receiver,
                alarm.type,
                alarm.item,
                alarm.checkAlarm,
                alarm.giver.userId,
                alarm.taker.userId
        )).from(alarm)
                .where(
                        alarm.taker.userId.eq(userId).and(alarm.checkAlarm.eq(false)),
                        checkUserType(type)
                )
                .orderBy(alarm.createdDate.desc())
                .fetch();
    }

//    동적 쿼리로 사용자의 형태에 따라 조회
    private BooleanExpression checkUserType(String type) {
        return type.equals("school") ? alarm.receiver.eq("school") : alarm.receiver.eq("people");
    }
}
