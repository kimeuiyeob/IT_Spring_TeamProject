package com.app.milestone.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_MONEY")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money extends Donation {
    private int moneyCash;

    @Builder
    public Money(int donationCount, int donationReceiveCount, int moneyCash) {
        super(donationCount, donationReceiveCount);
        this.moneyCash = moneyCash;
    }
}
