package com.app.milestone.domain;

import com.app.milestone.entity.Notice;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class NoticeDTO {
    private String noticeTitle;
    private String noticeContent;
    private Long noticeId;
    private LocalDateTime createdDate;

    public Notice toEntity() {
        return Notice.builder()
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .build();
    }

    @QueryProjection
    public NoticeDTO(Long noticeId, String noticeTitle, String noticeContent, LocalDateTime createdDate) {
        this.createdDate = createdDate;
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }
}
