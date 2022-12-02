package com.app.milestone.entity;

import com.app.milestone.domain.TalentDTO;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    private LocalDate talentAbleDate;
    @NotNull
    private String talentCategory;
    @NotNull
    private String talentPlace;

    @Builder //Builder pattern을 사용하게 해주는 어노테이션!
    public Talent(School school, People people, String talentTitle, String talentContent, LocalDate talentAbleDate, String talentCategory,String talentPlace) {
        super(school, people);
        this.talentTitle = talentTitle;
        this.talentContent = talentContent;
        this.talentAbleDate = talentAbleDate;
        this.talentCategory = talentCategory;
        this.talentPlace = talentPlace;

    }

    public void update(TalentDTO talentDTO) {
        Talent talent = talentDTO.toEntity();
        this.talentTitle = talent.talentTitle;
        this.talentContent = talent.talentContent;
        this.talentAbleDate = talent.talentAbleDate;
        this.talentCategory = talent.talentCategory;
        this.talentPlace = talent.talentPlace;
    }
}
