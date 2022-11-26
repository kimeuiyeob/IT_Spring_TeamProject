package com.app.milestone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_DONATION")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Donation extends Period {
    @Id
    @GeneratedValue
    private Long donationId;
    private int donationCount;
    private int donationReceiveCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private School school;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private People people;

    public Donation(int donationCount, int donationReceiveCount) {
        this.donationCount = donationCount;
        this.donationReceiveCount = donationReceiveCount;
    }
    public void update(int donationCount, int donationReceiveCount) {
        this.donationCount = donationCount;
        this.donationReceiveCount = donationReceiveCount;
    }
}
