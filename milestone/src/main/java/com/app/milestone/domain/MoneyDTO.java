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
    private String peopleNickName;
    private Long userId;
    private Long moneyCash;

    public Money toEntity() {
        return Money.builder()
                .moneyCash(moneyCash)
                .build();
    }

    @QueryProjection
    public MoneyDTO(String peopleNickName, Long userId, Long moneyCash) {
        this.peopleNickName = peopleNickName;
        this.userId = userId;
        this.moneyCash = moneyCash;
    }
}


