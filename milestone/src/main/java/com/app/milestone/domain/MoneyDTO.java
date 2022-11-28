package com.app.milestone.domain;


import com.app.milestone.entity.Money;
import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Component
@Data
@NoArgsConstructor
public class MoneyDTO {
    private Long peopleId;
    private Long schoolId;
    private int moneyCash;

    public Money toEntity() {
        return Money.builder()
                .moneyCash(moneyCash)
                .build();
    }

    @QueryProjection
    public MoneyDTO(Long peopleId, Long schoolId, int moneyCash) {
        this.peopleId = peopleId;
        this.schoolId = schoolId;
        this.moneyCash = moneyCash;
    }
}


