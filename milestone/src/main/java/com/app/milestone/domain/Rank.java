package com.app.milestone.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Rank {
    private Long donationItem;
    private Long userId;
}
