package com.app.milestone.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@DiscriminatorColumn(name = "DONATION_TYPE")
@Table(name = "TBL_DONATION")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Donation extends Period {
    @Id
    @GeneratedValue
    private Long donationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @NotNull
    private School school;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @NotNull
    private People people;

    public void changeSchool(School school) {
        this.school = school;
    }

    public void changePeople(People people) {
        this.people = people;
    }

    public Donation(School school, People people) {
        this.school = school;
        this.people = people;
    }
}
