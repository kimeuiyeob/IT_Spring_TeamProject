package com.app.milestone.domain;

import com.app.milestone.embeddable.Address;
import com.app.milestone.embeddable.Introduce;
import com.app.milestone.entity.School;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SchoolDTO {
    private String schoolName;
    private Address address;
    private int schoolTeachers;
    private int schoolKids;
    private int schoolBudget;
    private String schoolBank;
    private String schoolAccount;
    private String schoolPhoneNumber;
    private String schoolQR;
    private Introduce introduce;

    public School toEntity() {
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
                .build();
    }
}
