package com.app.milestone.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Ranking {
    private Long userId;
    private String userName;
    private Long rankingItem;
}
