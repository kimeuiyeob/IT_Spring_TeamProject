package com.app.milestone.entity;

import com.app.milestone.embeddable.Address;
import com.app.milestone.embeddable.Introduce;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_SCHOOL")
@DiscriminatorValue("school")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class School extends User {
    @NotNull
    private String schoolName;
    @Embedded
    private Address address;

    private int schoolTeachers;
    @NotNull
    private int schoolKids;

    private int schoolBudget;
    @NotNull
    private String schoolBank;
    @NotNull
    private String schoolAccount;
    @NotNull
    private String schoolPhoneNumber;
    @Column(name = "SCHOOL_QR")
    private String schoolQR;
    @Embedded
    private Introduce introduce;

//    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
//    private List<File> files;
//    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
//    private List<Reply> replies;
//    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
//    private List<Like> likes;
//    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
//    private List<Donation> donations;


    @Builder
    public School(String userEmail, String userName, String userPassword, String userPhoneNumber, String userProfile, int donationCount, String schoolName, Address address, int schoolTeachers, int schoolKids, int schoolBudget, String schoolBank, String schoolAccount, String schoolPhoneNumber, String schoolQR, Introduce introduce) {
        super(userEmail, userName, userPassword, userPhoneNumber, userProfile, donationCount);
        this.schoolName = schoolName;
        this.address = address;
        this.schoolTeachers = schoolTeachers;
        this.schoolKids = schoolKids;
        this.schoolBudget = schoolBudget;
        this.schoolBank = schoolBank;
        this.schoolAccount = schoolAccount;
        this.schoolPhoneNumber = schoolPhoneNumber;
        this.schoolQR = schoolQR;
        this.introduce = introduce;
    }

    public void update(String userEmail, String userName, String userPassword, String userPhoneNumber, String userProfile, int donationCount, String schoolName, Address address, int schoolTeachers, int schoolKids, int schoolBudget, String schoolBank, String schoolAccount, String schoolPhoneNumber, String schoolQR, Introduce introduce) {
        update(userEmail, userName, userPassword, userPhoneNumber, userProfile, donationCount);
        this.schoolName = schoolName;
        this.address = address;
        this.schoolTeachers = schoolTeachers;
        this.schoolKids = schoolKids;
        this.schoolBudget = schoolBudget;
        this.schoolBank = schoolBank;
        this.schoolAccount = schoolAccount;
        this.schoolPhoneNumber = schoolPhoneNumber;
        this.introduce = introduce;
    }
}
