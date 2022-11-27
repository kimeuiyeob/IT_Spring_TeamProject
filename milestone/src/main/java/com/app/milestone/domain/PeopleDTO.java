package com.app.milestone.domain;

import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PeopleDTO {
    private String peopleNickname;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userPhoneNumber;
    private String userProfile;
    private int donationCount;

    public People toEntity() {
        return People.builder()
                .peopleNickname(peopleNickname)
                .userEmail(userEmail)
                .userName(userName)
                .userPassword(userPassword)
                .userPhoneNumber(userPhoneNumber)
                .userProfile(userProfile)
                .donationCount(donationCount)
                .build();
    }

    @QueryProjection
    public PeopleDTO(String peopleNickname , String userEmail,String userName, String userPassword,String userPhoneNumber,String userProfile, int donationCount) {
        this.peopleNickname = peopleNickname;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.userProfile = userProfile;
        this.donationCount = donationCount;
    }
}
