package com.app.milestone.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_SERVICE")
@DiscriminatorValue("service")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Service extends Donation {
    @NotNull
    private LocalDateTime serviceVisitDate;


    public void changeSchool(School school){
        super.changeSchool(school);
    }
    public void changePeople(People people){
        super.changePeople(people);
    }

    @Builder
    public Service(LocalDateTime serviceVisitDate) {
        this.serviceVisitDate = serviceVisitDate;
    }

    public void update(LocalDateTime serviceVisitDate) {
        this.serviceVisitDate = serviceVisitDate;
    }

}
