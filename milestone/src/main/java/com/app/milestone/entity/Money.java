package com.app.milestone.entity;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("money")
@Table(name = "TBL_MONEY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money extends Donation {
    @NotNull
    private int moneyCash;

    @Builder
    public Money(int donationCount, int donationReceiveCount, int moneyCash) {
        this.moneyCash = moneyCash;
    }
}
