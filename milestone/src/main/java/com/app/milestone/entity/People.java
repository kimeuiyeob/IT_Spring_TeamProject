package com.app.milestone.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

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
//    @OneToMany(mappedBy = "people", fetch = FetchType.LAZY)
//    private List<Donation> donations;

    @Builder
    public People(String userEmail, String userName, String userPassword, String userPhoneNumber, String peopleNickname, int donationCount) {
        super(userEmail, userName, userPassword, userPhoneNumber, donationCount);
        this.peopleNickname = peopleNickname;
    }

    public void update(String peopleNickname) {
        this.peopleNickname = peopleNickname;
    }
}
