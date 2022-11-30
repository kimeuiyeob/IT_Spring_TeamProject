package com.app.milestone.domain;

import com.app.milestone.entity.Notice;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class NoticeDTO {
    private String noticeTitle;
    private String noticeContent;
    private Maintenance maintenance;

    public Notice toEntity() {
        return Notice.builder()
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .build();
    }

    @QueryProjection
    public NoticeDTO(String noticeTitle, String noticeContent) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }
}
