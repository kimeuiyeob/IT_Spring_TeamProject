package com.app.milestone.domain;

import com.app.milestone.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userPhoneNumber;
    private String userProfile;

    public User toEntity(){
        return User.builder()
                .userEmail(userEmail)
                .userName(userName)
                .userPassword(userPassword)
                .userPhoneNumber(userPhoneNumber)
                .userProfile(userProfile)
                .build();
    }

}
