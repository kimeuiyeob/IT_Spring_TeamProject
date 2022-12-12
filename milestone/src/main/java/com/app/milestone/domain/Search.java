package com.app.milestone.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class Search {

    private List<String> schoolAddress;
    private String schoolName;

    /*===============================*/

    private String keyword;
    private String talentTitle;
    private String talentCategory;
    private String talentPlaceOne;
    private List<String> talentPlace;

    /*===============================*/

    private String peopleUserId;
    private String peopleNickname;
    private String userName;

    /*===============================*/

    private String NoticeTitle;

    /*===============================*/

    private String withdrawalReason;
}
