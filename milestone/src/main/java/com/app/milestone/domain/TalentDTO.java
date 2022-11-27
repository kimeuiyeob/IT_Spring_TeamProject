package com.app.milestone.domain;

import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.app.milestone.entity.Talent;
import com.app.milestone.entity.User;
import com.app.milestone.type.Category;
import com.app.milestone.type.Place;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class TalentDTO {
    private String talentTitle;
    private String talentContent;
    private LocalDateTime talentAbleDate;
    private Category category;
    private Place place;

    public Talent toEntity() {
        return Talent.builder()
                .talentTitle(talentTitle)
                .talentContent(talentContent)
                .talentAbleDate(talentAbleDate)
                .category(category)
                .place(place)
                .build();
    }

    @QueryProjection
    public TalentDTO(String talentTitle, String talentContent, LocalDateTime talentAbleDate, Category category, Place place) {
        this.talentTitle = talentTitle;
        this.talentContent = talentContent;
        this.talentAbleDate = talentAbleDate;
        this.category = category;
        this.place = place;

    }
}
