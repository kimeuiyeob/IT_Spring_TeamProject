package com.app.milestone.entity;

import com.querydsl.jpa.impl.JPAQuery;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USER_TYPE")
@Entity
@Table(name = "TBL_USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Period {
    @Id
    @GeneratedValue
    protected Long userId;
    @NotNull
    protected String userEmail;
    @NotNull
    protected String userName;
    @NotNull
    protected String userPassword;
    @NotNull
    protected String userPhoneNumber;
    protected int donationCount;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<File> files;


    public User(String userEmail, String userName, String userPassword, String userPhoneNumber, int donationCount) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.donationCount = donationCount;
    }

    public void update(String userEmail, String userName, String userPassword, String userPhoneNumber, int donationCount) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.donationCount = donationCount;
    }
    public void update(int donationCount){
        this.donationCount = donationCount;
    }
}
