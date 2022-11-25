package com.app.milestone.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_WITHDRAWAL")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Withdrawal extends Period{
    @Id
    @GeneratedValue
    private Long withdrawalId;
    private String withdrawalReason;

    @Builder

    public Withdrawal(String withdrawalReason) {
        this.withdrawalReason = withdrawalReason;
    }
}
