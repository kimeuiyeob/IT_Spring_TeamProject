package com.app.milestone.domain;

import com.app.milestone.entity.School;
import com.app.milestone.entity.Talent;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

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

    private Long donationId;
    private String talentTitle;
    private String talentContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime talentAbleDate;
    private LocalDateTime createdDate;
    private String talentCategory;
    private String talentPlace;
    private String peopleNickname;
    private Long schoolUserId;
    private Long peopleUserId;
    private String peopleUserName;
    private String peopleUserEmail;

    private String fileName;
    private String filePath;
    private String fileUuid;
    private Long fileSize;
    private boolean fileImageCheck;
    private String fileType;

    //profile에필요한필드


    public Talent toEntity() { //Service 단에서, 데이터를 저장시키기 위해서, DTO를 Entity로 변환해주는 메소드
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
    public TalentDTO(Long donationId,String talentTitle, String talentContent, LocalDateTime talentAbleDate, LocalDateTime createdDate, String talentCategory, String talentPlace,  String peopleNickname, Long schoolUserId, Long peopleUserId, String peopleUserName, String peopleUserEmail) {
        this.donationId = donationId;
        this.talentTitle = talentTitle;
        this.talentContent = talentContent;
        this.talentAbleDate = talentAbleDate;
        this.createdDate = createdDate;
        this.talentCategory = talentCategory;
        this.talentPlace = talentPlace;
        this.peopleNickname = peopleNickname;
        this.schoolUserId = schoolUserId;
        this.peopleUserId = peopleUserId;
        this.peopleUserEmail = peopleUserEmail;
        this.peopleUserName = peopleUserName;

    }

    @QueryProjection
    public TalentDTO(Long donationId, String talentTitle, String talentContent, LocalDateTime talentAbleDate, LocalDateTime createdDate, String talentCategory, String talentPlace, String peopleNickname, Long schoolUserId, Long peopleUserId, String peopleUserName, String peopleUserEmail, String fileName, String filePath, String fileUuid, Long fileSize, boolean fileImageCheck, String fileType) {
        this.donationId = donationId;
        this.talentTitle = talentTitle;
        this.talentContent = talentContent;
        this.talentAbleDate = talentAbleDate;
        this.createdDate = createdDate;
        this.talentCategory = talentCategory;
        this.talentPlace = talentPlace;
        this.peopleNickname = peopleNickname;
        this.schoolUserId = schoolUserId;
        this.peopleUserId = peopleUserId;
        this.peopleUserName = peopleUserName;
        this.peopleUserEmail = peopleUserEmail;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileImageCheck = fileImageCheck;
        this.fileType = fileType;
    }
}
