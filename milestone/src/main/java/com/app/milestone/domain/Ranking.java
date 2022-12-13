package com.app.milestone.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Ranking {
    private Long userId;
    private String userName;
    private String peopleNickname;
    private Long rankingItem;
    private FileDTO file;
}
