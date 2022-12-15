package com.app.milestone.domain;

import com.app.milestone.entity.School;
import com.app.milestone.entity.Service;
import com.app.milestone.entity.Talent;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class ServiceDTO {
    private String schoolName;
    private String peopleNickname;
    private Long userId;
    private String schoolAddress;
    private String schoolAddressDetail;
    private Long donationId;
    private String peopleUserName;
    private String peopleUserEmail;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime serviceVisitDate;

    public Service toEntity() {
        return Service.builder()
                .serviceVisitDate(serviceVisitDate)
                .build();
    }

    @QueryProjection
    public ServiceDTO(String schoolName, String peopleNickname, Long userId, String schoolAddress, String schoolAddressDetail, LocalDateTime serviceVisitDate, Long donationId,String peopleUserName, String peopleUserEmail) {
        this.schoolName = schoolName;
        this.peopleNickname = peopleNickname;
        this.userId = userId;
        this.schoolAddress = schoolAddress;
        this.schoolAddressDetail = schoolAddressDetail;
        this.serviceVisitDate = serviceVisitDate;
        this.donationId = donationId;
        this.peopleUserName = peopleUserName;
        this.peopleUserEmail = peopleUserEmail;
    }
}
