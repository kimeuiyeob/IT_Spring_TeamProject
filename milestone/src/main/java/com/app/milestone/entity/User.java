package com.app.milestone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_USER")
@Getter
@ToString(exclude = "userPassword")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class User extends Period {
    @Id
    @GeneratedValue
    private Long userId;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userPhoneNumber;
    private String userProfile;


    public User(String userEmail, String userName, String userPassword, String userPhoneNumber, String userProfile) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.userProfile = userProfile;
    }
    public void update(String userEmail, String userName, String userPassword, String userPhoneNumber, String userProfile) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.userProfile = userProfile;
    }
}
