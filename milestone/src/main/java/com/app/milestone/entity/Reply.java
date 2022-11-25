package com.app.milestone.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_REPLY")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends Period {
    @Id
    @GeneratedValue
    private Long replyId;
    private String replyContent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private School school;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private People people;

    @Builder
    public Reply(String replyContent) {
        this.replyContent = replyContent;
    }
}
