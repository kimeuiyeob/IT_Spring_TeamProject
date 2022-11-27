package com.app.milestone.entity;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_SERVICE")
@DiscriminatorValue("service")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Service extends Donation {
    private LocalDateTime serviceVisitDate;

    @Builder
    public Service(LocalDateTime serviceVisitDate) {
        this.serviceVisitDate = serviceVisitDate;
    }
}
