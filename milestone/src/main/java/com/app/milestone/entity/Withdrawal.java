package com.app.milestone.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_WITHDRAWAL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Withdrawal extends Period {
    @Id
    @GeneratedValue
    private Long withdrawalId;
    @NotNull
    private String withdrawalReason;

    @Builder
    public Withdrawal(String withdrawalReason) {
        this.withdrawalReason = withdrawalReason;
    }
}
