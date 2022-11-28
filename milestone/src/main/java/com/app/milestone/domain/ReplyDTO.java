package com.app.milestone.domain;

import com.app.milestone.entity.Reply;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ReplyDTO {
    private String replyContent;
    private Long schoolId;
    private Long peopleId;

    public Reply toEntity() {
        return Reply.builder()
                .replyContent(replyContent)
                .build();
    }

    @QueryProjection
    public ReplyDTO(String replyContent, Long schoolId, Long peopleId) {
        this.replyContent = replyContent;
        this.schoolId = schoolId;
        this.peopleId = peopleId;
    }
}
