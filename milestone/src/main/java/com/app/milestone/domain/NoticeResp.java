package com.app.milestone.domain;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@Data
public class NoticeResp {
    private Long total;
    private Page<NoticeDTO> arNoticeDTO;
}
