package com.app.milestone.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private People people;

    @Builder
    public Like(School school, People people) {
        this.school = school;
        this.people = people;
    }


}
