package com.app.milestone.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserDTO {
    private String peopleNickname;
    private String schoolName;
    private FileDTO file;
}
