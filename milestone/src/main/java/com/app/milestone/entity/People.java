package com.app.milestone.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_PEOPLE")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class People extends User {
    private String peopleNickname;

//    @OneToMany(mappedBy = "people", fetch = FetchType.LAZY)
//    private List<Reply> replies;
//    @OneToMany(mappedBy = "people", fetch = FetchType.LAZY)
//    private List<Like> likes;
//    @OneToMany(mappedBy = "people", fetch = FetchType.LAZY)
//    private List<Donation> donations;


    @Builder

    public People(String peopleNickname) {
        this.peopleNickname = peopleNickname;
    }
}
