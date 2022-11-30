package com.app.milestone.entity;

import com.app.milestone.type.Maintenance;
import com.app.milestone.type.Place;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_NOTICE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends Period {
    @Id
    @GeneratedValue
    private Long noticeId;
    @NotNull
    private String noticeTitle;
    @NotNull
    private String noticeContent;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Maintenance maintenance;

    @Builder
    public Notice(String noticeTitle, String noticeContent, Maintenance maintenance) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.maintenance = maintenance;
    }

    public void update(String noticeTitle, String noticeContent, Maintenance maintenance) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.maintenance = maintenance;
    }

}
