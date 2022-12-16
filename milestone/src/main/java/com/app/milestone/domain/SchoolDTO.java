package com.app.milestone.domain;

import com.app.milestone.embeddable.Address;
import com.app.milestone.embeddable.Introduce;
import com.app.milestone.entity.School;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class SchoolDTO {
    private Long userId;
    private String schoolName;
    private String schoolZipcode;
    private String schoolAddress;
    private String schoolAddressDetail;
    private int schoolTeachers;
    private int schoolKids;
    private int schoolBudget;
    private String schoolBank;
    private String schoolAccount;
    private String schoolPhoneNumber;
    private String schoolQR;
    private String schoolTitle;
    private String schoolContent;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userPhoneNumber;
    private FileDTO userProfile;
    private int donationCount;
    private LocalDateTime createdDate;
    private List<FileDTO> files;


    public School toEntity() {
        Address address = new Address();
        Introduce introduce = new Introduce();

        address.setSchoolZipcode(schoolZipcode);
        address.setSchoolAddress(schoolAddress);
        address.setSchoolAddressDetail(schoolAddressDetail);
        introduce.setSchoolTitle(schoolTitle);
        introduce.setSchoolContent(schoolContent);
        return School.builder()
                .schoolName(schoolName)
                .address(address)
                .schoolTeachers(schoolTeachers)
                .schoolKids(schoolKids)
                .schoolBudget(schoolBudget)
                .schoolBank(schoolBank)
                .schoolAccount(schoolAccount)
                .schoolPhoneNumber(schoolPhoneNumber)
                .schoolQR(schoolQR)
                .introduce(introduce)
                .userEmail(userEmail)
                .userName(userName)
                .userPassword(userPassword)
                .userPhoneNumber(userPhoneNumber)
                .donationCount(donationCount)
                .build();
    }

    @QueryProjection
    public SchoolDTO(Long userId, String schoolName, String schoolAddress, String schoolAddressDetail, String schoolZipcode, int schoolTeachers, int schoolKids, int schoolBudget, String schoolBank, String schoolAccount, String schoolPhoneNumber, String schoolQR, String schoolTitle, String schoolContent, String userEmail, String userName, String userPassword, String userPhoneNumber, int donationCount, LocalDateTime createdDate) {
        this.userId = userId;
        this.schoolName = schoolName;
        this.schoolAddress = schoolAddress;
        this.schoolAddressDetail = schoolAddressDetail;
        this.schoolZipcode = schoolZipcode;
        this.schoolTeachers = schoolTeachers;
        this.schoolKids = schoolKids;
        this.schoolBudget = schoolBudget;
        this.schoolBank = schoolBank;
        this.schoolAccount = schoolAccount;
        this.schoolPhoneNumber = schoolPhoneNumber;
        this.schoolQR = schoolQR;
        this.schoolTitle = schoolTitle;
        this.schoolContent = schoolContent;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.donationCount = donationCount;
        this.createdDate = createdDate;
    }
}
