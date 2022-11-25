package com.app.milestone.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_NOTICE")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends Period {
    @Id
    @GeneratedValue
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;

    @Builder
    public Notice(String noticeTitle, String noticeContent) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }
}
