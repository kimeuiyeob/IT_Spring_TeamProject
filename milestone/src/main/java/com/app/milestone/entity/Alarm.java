package com.app.milestone.entity;

import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_ALARM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends Period {
    @Id
    @GeneratedValue
    private Long alarmId;
    @NotNull
    private String receiver;
    @NotNull
    private String type;
    @NotNull
    private String item;
    private Boolean checkAlarm;

    @NotNull
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User giver;
    @NotNull
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User taker;

    public void changeGiver(User giver) {
        this.giver = giver;
    }

    public void changeTaker(User taker) {
        this.taker = taker;
    }

    @Builder
    public Alarm(String receiver, String type, String item, Boolean checkAlarm) {
        this.receiver = receiver;
        this.type = type;
        this.item = item;
        this.checkAlarm = checkAlarm;
    }

    public void updateCheckAlarm(Boolean checkAlarm){
        this.checkAlarm = checkAlarm;
    }
}
