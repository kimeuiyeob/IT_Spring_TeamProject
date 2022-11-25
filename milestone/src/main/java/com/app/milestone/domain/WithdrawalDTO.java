package com.app.milestone.domain;

import com.app.milestone.entity.User;
import com.app.milestone.entity.Withdrawal;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class WithdrawalDTO {
    private Long withdrawalId;
    private String withdrawalReason;

    public Withdrawal toEntity() {
        return Withdrawal.builder()
                .withdrawalReason(withdrawalReason)
                .build();
    }
}
