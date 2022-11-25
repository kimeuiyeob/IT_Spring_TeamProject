package com.app.milestone.domain;

import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PeopleDTO {
    private String peopleNickname;

    public People toEntity() {
        return People.builder()
                .peopleNickname(peopleNickname)
                .build();
    }
}
