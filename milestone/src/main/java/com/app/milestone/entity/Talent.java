package com.app.milestone.entity;

import com.app.milestone.domain.TalentDTO;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity //DB가 해당 객체를 인식 가능
@Table(name = "TBL_TALENT") //테이블간에 매핑
@DiscriminatorValue("talent") //@DiscriminatorColumn 부모 / @DiscriminatorValue("talent") 자식 talent이름으로 구분을 지어준다.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Talent extends Donation {

    @NotNull //@NonNull어노테이션 -> 필드에 Null 값이 주입될 경우 NPE이 뜬다.
    private String talentTitle;
    @NotNull
    private String talentContent;
    @NotNull
    private LocalDateTime talentAbleDate;
    @NotNull
    private String talentCategory;
    @NotNull
    private String talentPlace;

    public void changeSchool(School school){
        super.changeSchool(school);
    }
    public void changePeople(People people){
        super.changePeople(people);
    }
    @Builder //Builder pattern을 사용하게 해주는 어노테이션!
    public Talent(String talentTitle, String talentContent, LocalDateTime talentAbleDate, String talentCategory,String talentPlace) {
        this.talentTitle = talentTitle;
        this.talentContent = talentContent;
        this.talentAbleDate = talentAbleDate;
        this.talentCategory = talentCategory;
        this.talentPlace = talentPlace;
    }

    public void update(TalentDTO talent) {
        this.talentTitle = talent.getTalentTitle();
        this.talentContent = talent.getTalentContent();
        this.talentAbleDate = talent.getTalentAbleDate();
        this.talentCategory = talent.getTalentCategory();
        this.talentPlace = talent.getTalentPlace();

    }
}
