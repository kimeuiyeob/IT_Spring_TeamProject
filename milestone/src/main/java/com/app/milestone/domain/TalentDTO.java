package com.app.milestone.domain;

import com.app.milestone.entity.Talent;
import com.app.milestone.type.Place;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*================================================================================*/
//                       DTO             DTO        Entity          Entity
//순서 : Client(browser)  =>  Controller  =>  Service  =>  Repository  =>  DB
/*================================================================================*/
//굳이 이렇게 DTO 클래스를 새로 만들어서 사용하는 이유!
//서비스 단은 데이터베이스와 독립적이어야 합니다.
//데이터베이스의 변경사항이 있으면, Entity를 사용하는 서비스 단에도 변경이 일어날 수 있습니다.
/*================================================================================*/

@Component
@Data
@NoArgsConstructor
public class TalentDTO {

    private Long userId;
    private String talentTitle;
    private String talentContent;
    private LocalDate talentAbleDate;
    private String talentCategory;
    private String talentPlace;

    public Talent toEntity() { //Service 단에서, 데이터를 저장시키기 위해서, 이 DTO를 Entity로 변환해주는 메소드
        return Talent.builder()
                //빌더 패턴으로 구현하면 각 값들은 빌더의 각 값들의 이름 함수로 셋팅이 되지 각각 무슨 값을 의미하는지 파악하기 쉽다.
                //따라서 생성자로 설정해야하는 값이 많을 경우 빌더를 쓰는 것이 생성자보다 가독성이 좋다
                .talentTitle(talentTitle)
                .talentContent(talentContent)
                .talentAbleDate(talentAbleDate)
                .talentCategory(talentCategory)
                .talentPlace(talentPlace)
                .build();
    }

    @QueryProjection
    //@QueryProjection은 생성자를 통해 DTO를 조회 -> Q파일 생성 , DTO의 생성자를 사용하는 것이 아니라 DTO 기반으로 생성된 QDTO 객체의 생성자를 사용하는 것이다.
    public TalentDTO(Long userId,String talentTitle, String talentContent, LocalDate talentAbleDate, String talentCategory, String talentPlace) {
        this.userId = userId;
        this.talentTitle = talentTitle;
        this.talentContent = talentContent;
        this.talentAbleDate = talentAbleDate;
        this.talentCategory = talentCategory;
        this.talentPlace = talentPlace;

    }
}
