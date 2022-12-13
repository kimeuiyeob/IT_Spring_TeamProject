package com.app.milestone.entity;

import com.app.milestone.domain.PasswordDTO;
import com.app.milestone.domain.PeopleDTO;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_PEOPLE")
@DiscriminatorValue("people")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class People extends User {
    @NotNull
    private String peopleNickname;

    //    @OneToMany(mappedBy = "people", fetch = FetchType.LAZY)
//    private List<Reply> replies;
//    @OneToMany(mappedBy = "people", fetch = FetchType.LAZY)
//    private List<Like> likes;
    @OneToMany(mappedBy = "people", fetch = FetchType.LAZY)
    private List<Donation> donations;

    @Builder
    public People(String userEmail, String userName, String userPassword, String userPhoneNumber, String peopleNickname, int donationCount) {
        super(userEmail, userName, userPassword, userPhoneNumber, donationCount);
        this.peopleNickname = peopleNickname;
    }

    public void update(String peopleNickname) {
        this.peopleNickname = peopleNickname;
    }

    public void update(PeopleDTO peopleDTO) {
//        School school = schoolDTO.toEntity();
        this.userEmail = peopleDTO.getUserEmail();
        this.userName = peopleDTO.getUserName();
        this.peopleNickname = peopleDTO.getPeopleNickname();
        this.userPhoneNumber = peopleDTO.getUserPhoneNumber();
    }
    public void updatePassword(PasswordDTO passwordDTO) {
//        People people = peopleDTO.toEntity();
        this.userPassword= passwordDTO.getChangePassword();
    }
}
