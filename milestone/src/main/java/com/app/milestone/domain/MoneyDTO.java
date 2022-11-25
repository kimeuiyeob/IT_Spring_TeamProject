package com.app.milestone.domain;


import com.app.milestone.entity.Money;
import com.app.milestone.entity.School;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MoneyDTO {
    private int moneyCash;

    public Money toEntity() {
        return Money.builder()
                .moneyCash(moneyCash)
                .build();
    }
}
