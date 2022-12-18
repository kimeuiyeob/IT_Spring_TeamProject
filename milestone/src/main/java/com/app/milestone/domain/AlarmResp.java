package com.app.milestone.domain;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@Data
public class AlarmResp {
    private Long total;
    private Page<AlarmDTO> arAlarmDTO;
}
