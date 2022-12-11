package com.app.milestone.domain;


import com.app.milestone.entity.Money;
import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class MoneyDTO {
    private String schoolName;
    private String peopleNickname;
    private Long userId;
    private Long moneyCash;
    private LocalDateTime createdDate;

    public Money toEntity() {
        return Money.builder()
                .moneyCash(moneyCash)
                .build();
    }

    @QueryProjection
    public MoneyDTO(String schoolName, String peopleNickname, Long userId, Long moneyCash, LocalDateTime createdDate) {
        this.schoolName = schoolName;
        this.peopleNickname = peopleNickname;
        this.userId = userId;
        this.moneyCash = moneyCash;
        this.createdDate = createdDate;
    }
}


