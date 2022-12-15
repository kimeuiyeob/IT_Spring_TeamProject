package com.app.milestone.domain;

import com.app.milestone.entity.Alarm;
import com.app.milestone.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class AlarmDTO {
    private Long alarmId;
    private String receiver;
    private String type;
    private String item;
    private Boolean checkAlarm;
    private String name;
    private String PhoneNumber;
    private Long giverId;
    private Long takerId;

    public Alarm toEntity() {
        return Alarm.builder()
                .receiver(receiver)
                .type(type)
                .item(item)
                .checkAlarm(checkAlarm)
                .build();
    }

    @QueryProjection
    public AlarmDTO(Long alarmId, String receiver, String type, String item, Boolean checkAlarm, Long giverId, Long takerId) {
        this.alarmId = alarmId;
        this.receiver = receiver;
        this.type = type;
        this.item = item;
        this.checkAlarm = checkAlarm;
        this.giverId = giverId;
        this.takerId = takerId;
    }
}
