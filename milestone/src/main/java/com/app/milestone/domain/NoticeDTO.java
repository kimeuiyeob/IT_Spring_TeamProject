package com.app.milestone.domain;

import com.app.milestone.entity.Notice;
import com.app.milestone.entity.School;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class NoticeDTO {
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;

    public Notice toEntity() {
        return Notice.builder()
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .build();
    }
}
