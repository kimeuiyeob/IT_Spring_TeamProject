package com.app.milestone.domain;

import com.app.milestone.entity.User;
import com.app.milestone.entity.Withdrawal;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class WithdrawalDTO {
    private String withdrawalReason;
    private String withdrawalUserType;

    public Withdrawal toEntity() {
        return Withdrawal.builder()
                .withdrawalReason(withdrawalReason)
                .withdrawalUserType(withdrawalUserType)
                .build();
    }

    @QueryProjection
    public WithdrawalDTO(String withdrawalReason, String withdrawalUserType) {
        this.withdrawalReason = withdrawalReason;
        this.withdrawalUserType = withdrawalUserType;
    }
}
