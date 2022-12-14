package com.app.milestone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PasswordDTO {
    private String changePassword;
}
