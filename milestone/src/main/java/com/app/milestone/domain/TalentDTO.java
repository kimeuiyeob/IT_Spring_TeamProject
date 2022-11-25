package com.app.milestone.domain;

import com.app.milestone.entity.Talent;
import com.app.milestone.entity.User;
import com.app.milestone.type.Category;
import com.app.milestone.type.Place;
import lombok.Data;
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
}
