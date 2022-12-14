package com.app.milestone.entity;

import com.app.milestone.domain.PasswordDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.embeddable.Address;
import com.app.milestone.embeddable.Introduce;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
    @Size(max = 50)
    private String schoolBank;
    @NotNull
    @Size(max = 100)
    private String schoolAccount;
    @NotNull
    @Size(max = 500)
    private String schoolPhoneNumber;
    @Column(name = "SCHOOL_QR")
    @Size(max = 500)
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
    public School(String userEmail, String userName, String userPassword, String userPhoneNumber, int donationCount, String schoolName, Address address, int schoolTeachers, int schoolKids, int schoolBudget, String schoolBank, String schoolAccount, String schoolPhoneNumber, String schoolQR, Introduce introduce) {
        super(userEmail, userName, userPassword, userPhoneNumber, donationCount);
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

    public void update(SchoolDTO schoolDTO) {
//        School school = schoolDTO.toEntity();
        Address address = new Address();
        address.setSchoolZipcode(schoolDTO.getSchoolZipcode());
        address.setSchoolAddress(schoolDTO.getSchoolAddress());
        address.setSchoolAddressDetail(schoolDTO.getSchoolAddressDetail());
        Introduce introduce = new Introduce();
        introduce.setSchoolTitle(schoolDTO.getSchoolTitle());
        introduce.setSchoolContent(schoolDTO.getSchoolContent());

        this.userEmail = schoolDTO.getUserEmail();
        this.userName = schoolDTO.getUserName();
//        this.userPassword = schoolDTO.getUserPassword();
        this.schoolName = schoolDTO.getSchoolName();
        this.address = address;
        this.userPhoneNumber = schoolDTO.getUserPhoneNumber();
        this.schoolPhoneNumber = schoolDTO.getSchoolPhoneNumber();
        this.schoolTeachers = schoolDTO.getSchoolTeachers();
        this.schoolKids = schoolDTO.getSchoolKids();
        this.schoolBudget = schoolDTO.getSchoolBudget();
        this.schoolBank = schoolDTO.getSchoolBank();
        this.schoolAccount = schoolDTO.getSchoolAccount();
        this.introduce = introduce;
        this.schoolQR = schoolDTO.getSchoolQR();
    }

    public void updatePassword(PasswordDTO passwordDTO) {
//        People people = peopleDTO.toEntity();
        this.userPassword = passwordDTO.getChangePassword();
    }

//    public void update(SchoolDTO schoolDTO) {
//        School school = schoolDTO.toEntity();
//        this.userEmail = school.userEmail;
//        this.userName = school.userName;
//        this.userPassword = school.userPassword;
//        this.userPhoneNumber = school.userPhoneNumber;
//        this.donationCount = school.donationCount;
//        this.schoolName = school.schoolName;
//        this.address = school.address;
//        this.schoolTeachers = school.schoolTeachers;
//        this.schoolKids = school.schoolKids;
//        this.schoolBudget = school.schoolBudget;
//        this.schoolBank = school.schoolBank;
//        this.schoolAccount = school.schoolAccount;
//        this.schoolPhoneNumber = school.schoolPhoneNumber;
//        this.introduce = school.introduce;
//    }

//    public SchoolDTO toDTO() {
//        return new SchoolDTO(schoolName, address.getSchoolAddress(), address.getSchoolAddressDetail(), address.getSchoolZipcode(), schoolTeachers, schoolKids, schoolBudget, schoolBank, schoolAccount, schoolPhoneNumber, schoolQR, introduce.getSchoolTitle(), introduce.getSchoolContent(), userEmail, userName, userPassword, userPhoneNumber, donationCount);
//    }

}
