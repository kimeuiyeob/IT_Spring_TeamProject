package com.app.milestone.domain;

import com.app.milestone.entity.People;
import com.app.milestone.entity.Reply;
import com.app.milestone.entity.School;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class ReplyDTO {
    private Long replyId;
    private String replyContent;
    private Long schoolUserId;
    private Long peopleUserId;
    private String peopleNickName;
    private LocalDateTime createdDate;

    public Reply toEntity() {
        return Reply.builder()
                .replyContent(replyContent)
                .build();
    }

    @QueryProjection
    public ReplyDTO(Long replyId, String replyContent,String peopleNickName, LocalDateTime createdDate, Long schoolUserId, Long peopleUserId) {
        this.replyId = replyId;
        this.replyContent = replyContent;
        this.peopleNickName = peopleNickName;
        this.createdDate = createdDate;
        this.schoolUserId = schoolUserId;
        this.peopleUserId = peopleUserId;
    }
}
