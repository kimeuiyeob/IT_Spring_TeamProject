package com.app.milestone.domain;

import com.app.milestone.entity.Like;
import com.app.milestone.entity.School;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class LikeDTO {
    private SchoolDTO schoolDTO;
    private PeopleDTO peopleDTO;


    @QueryProjection
    public LikeDTO(SchoolDTO schoolDTO, PeopleDTO peopleDTO) {
        this.schoolDTO = schoolDTO;
        this.peopleDTO = peopleDTO;
    }
}