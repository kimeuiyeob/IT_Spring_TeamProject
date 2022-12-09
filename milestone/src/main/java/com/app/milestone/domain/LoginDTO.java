package com.app.milestone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
@Data
@NoArgsConstructor
public class LoginDTO {
    private Long userId;
    @NotEmpty
    private String userEmail;
    @NotEmpty
    private String userPassword;
}
