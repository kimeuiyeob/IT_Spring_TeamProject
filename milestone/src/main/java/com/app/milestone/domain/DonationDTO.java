package com.app.milestone.domain;

import com.app.milestone.entity.Donation;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class DonationDTO {
    private Long donationId;
    private int donationCount;
    private int donationReceiveCount;
    private SchoolDTO schoolDTO;
    private PeopleDTO peopleDTO;

    public Donation toEntity() {
        return Donation.builder()
                .donationCount(donationCount)
                .donationReceiveCount(donationReceiveCount)
                .build();
    }
}
