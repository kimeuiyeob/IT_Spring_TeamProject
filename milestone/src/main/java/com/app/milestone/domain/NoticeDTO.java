package com.app.milestone.domain;

import com.app.milestone.entity.Notice;
import com.app.milestone.entity.School;
import com.app.milestone.type.Maintenance;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
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
                .maintenance(maintenance)
                .build();
    }

    @QueryProjection
    public NoticeDTO(String noticeTitle, String noticeContent, Maintenance maintenance) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.maintenance = maintenance;
    }
}
