package com.app.milestone.entity;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("money")
@Table(name = "TBL_MONEY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money extends Donation {
    @NotNull
    private Long moneyCash;

    @Builder
    public Money(Long moneyCash) {
        this.moneyCash = moneyCash;
    }

    public void changeSchool(School school){
        super.changeSchool(school);
    }

    public void changePeople(People people){
        super.changePeople(people);
    }
}
