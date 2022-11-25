package com.app.milestone.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_LIKE")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends Period{
    @Id
    @GeneratedValue
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private School school;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private People people;
}
