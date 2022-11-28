package com.app.milestone.domain;

import com.app.milestone.entity.Like;
import com.app.milestone.entity.School;
import com.app.milestone.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class LikeDTO {
    private Long schoolId;
    private Long peopleId;


    @QueryProjection
    public LikeDTO(Long schoolId, Long peopleId) {
        this.schoolId = schoolId;
        this.peopleId = peopleId;
    }
}