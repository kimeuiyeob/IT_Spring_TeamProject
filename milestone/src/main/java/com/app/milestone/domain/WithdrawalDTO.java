package com.app.milestone.domain;

import com.app.milestone.entity.Withdrawal;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class WithdrawalDTO {
    private String withdrawalReason;
    private String withdrawalUserType;
    private LocalDateTime createdDate;

    public Withdrawal toEntity() {
        return Withdrawal.builder()
                .withdrawalReason(withdrawalReason)
                .withdrawalUserType(withdrawalUserType)
                .build();
    }


    @QueryProjection
    public WithdrawalDTO(String withdrawalReason, String withdrawalUserType, LocalDateTime createdDate) {
        this.withdrawalReason = withdrawalReason;
        this.withdrawalUserType = withdrawalUserType;
        this.createdDate = createdDate;
    }
}
