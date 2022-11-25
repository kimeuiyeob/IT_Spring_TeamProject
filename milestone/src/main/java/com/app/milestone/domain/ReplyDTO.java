package com.app.milestone.domain;

import com.app.milestone.entity.Reply;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ReplyDTO {
    private Long replyId;
    private String replyContent;
    private SchoolDTO schoolDTO;
    private PeopleDTO peopleDTO;

    public Reply toEntity() {
        return Reply.builder()
                .replyContent(replyContent)
                .build();
    }

    @QueryProjection
    public ReplyDTO(Long replyId, String replyContent, SchoolDTO schoolDTO, PeopleDTO peopleDTO) {
        this.replyId = replyId;
        this.replyContent = replyContent;
        this.schoolDTO = schoolDTO;
        this.peopleDTO = peopleDTO;
    }
}
