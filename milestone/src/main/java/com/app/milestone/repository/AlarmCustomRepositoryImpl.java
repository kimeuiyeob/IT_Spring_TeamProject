package com.app.milestone.repository;

import com.app.milestone.domain.AlarmDTO;
import com.app.milestone.domain.QAlarmDTO;
import com.app.milestone.entity.QAlarm;
import com.app.milestone.entity.QPeople;
import com.app.milestone.entity.QSchool;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.milestone.entity.QAlarm.*;
import static com.app.milestone.entity.QPeople.*;
import static com.app.milestone.entity.QSchool.*;
import static com.app.milestone.entity.QService.service;

@Repository
@RequiredArgsConstructor
public class AlarmCustomRepositoryImpl implements AlarmCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    //====================황지수====================
    //    전체 알람
    //  마이페이지에서 사용할 모든 알람을 가져와 페이징 처리를 할 수 있게 하였다.
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

    //====================황지수====================
    //    안읽은 알람 전체
    //  헤더에 있는 알람에 보여질 안읽은 알람을 조회 한다. 각 회원에 맞는 알람을 보여주기위해 동적쿼리를 사용했다.
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

    //====================황지수====================
    //    동적 쿼리로 사용자의 형태에 따라 조회
    //  알람 조회시 각회원에 따라 보여질 알람이 다르기 때문에 상황에 따른 조건문을 돌려준다.
    private BooleanExpression checkUserType(String type) {
        return type.equals("school") ? alarm.receiver.eq("school") : alarm.receiver.eq("people");
    }

    @Override
    public Long countAlarm(Pageable pageable, Long userId) {
        return jpaQueryFactory.select(alarm.count())
                .from(alarm)
                .where(alarm.taker.userId.eq(userId))
                .orderBy(alarm.createdDate.asc())
                .fetchOne();
    }

    @Override
    public List<AlarmDTO> findAlarmByType1(Long userId, Pageable pageable, String type) {
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
    @Override
    public List<AlarmDTO> findAlarmByType2(Long userId, Pageable pageable, String type) {
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
}
