package com.app.milestone.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USER_TYPE")
@Entity
@Table(name = "TBL_USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class User extends Period {
    @Id
    @GeneratedValue
    private Long userId;
    @NotNull
    private String userEmail;
    @NotNull
    private String userName;
    @NotNull
    private String userPassword;
    @NotNull
    private String userPhoneNumber;
    private String userProfile;
    private int donationCount;


    public User(String userEmail, String userName, String userPassword, String userPhoneNumber, String userProfile, int donationCount) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.userProfile = userProfile;
        this.donationCount = donationCount;
    }

    public void update(String userEmail, String userName, String userPassword, String userPhoneNumber, String userProfile) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.userProfile = userProfile;
    }
}
