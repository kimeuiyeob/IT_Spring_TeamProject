package com.app.milestone.domain;

import com.app.milestone.entity.Reply;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@Data
@NoArgsConstructor
public class ReplyDTO {
//    private Long replyId;
    private String replyContent;
    private Long userId;

    public Reply toEntity() {
        return Reply.builder()
                .replyContent(replyContent)
                .build();
    }

    @QueryProjection
    public ReplyDTO(String replyContent, Long userId) {
        this.replyContent = replyContent;
        this.userId = userId;
    }
}
