package com.app.milestone.entity;

import com.app.milestone.domain.TalentDTO;
import com.app.milestone.type.Place;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
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
    private String category;
    @NotNull
//    @Enumerated(EnumType.STRING) //enum클래스로 만든걸 여기서 @Enumerated사용, 타입구분지을때 사용
    private String place;

    @Builder //Builder pattern을 사용하게 해주는 어노테이션!
    public Talent(School school, People people, String talentTitle, String talentContent, LocalDateTime talentAbleDate, String category,String place) {
        super(school, people);
        this.talentTitle = talentTitle;
        this.talentContent = talentContent;
        this.talentAbleDate = talentAbleDate;
        this.category = category;
        this.place = place;

    }

    public void update(TalentDTO talentDTO) {
        Talent talent = talentDTO.toEntity();
        this.talentTitle = talent.talentTitle;
        this.talentContent = talent.talentContent;
        this.talentAbleDate = talent.talentAbleDate;
        this.category = talent.category;
        this.place = talent.place;
    }
}
