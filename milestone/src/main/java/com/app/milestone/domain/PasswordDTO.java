package com.app.milestone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
@Data
@NoArgsConstructor
public class PasswordDTO {
    private String changePassword;
}
