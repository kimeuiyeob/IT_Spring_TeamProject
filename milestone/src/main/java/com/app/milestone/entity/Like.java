package com.app.milestone.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_LIKE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends Period {
    @Id
    @GeneratedValue
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @NotNull
    private School school;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @NotNull
    private People people;

    @Builder
    public Like(School school, People people) {
        this.school = school;
        this.people = people;
    }


}
