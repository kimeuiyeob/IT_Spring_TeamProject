package com.app.milestone.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TBL_REPLY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends Period {
    @Id
    @GeneratedValue
    private Long replyId;
    @NotNull
    private String replyContent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    @NotNull
    private School school;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @NotNull
    private User user;

    public void changeSchool(School school) {
        this.school = school;
    }

    public void changePeople(User user) {
        this.user = user;
    }

    @Builder
    public Reply(String replyContent, School school, User user) {
        this.replyContent = replyContent;
        this.school = school;
        this.user = user;
    }

    public void update(String replyContent) {
        this.replyContent = replyContent;
    }
}
