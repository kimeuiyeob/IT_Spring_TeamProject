package com.app.milestone.domain;

import com.app.milestone.embeddable.Address;
import com.app.milestone.entity.Like;
import com.app.milestone.entity.School;
import com.app.milestone.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class LikeDTO {
    private Long likeId;
    private Long schoolId;
    private Long peopleId;
    private String schoolName;
    private String schoolContent;
    private String schoolAddress;
    private String schoolQr;
    private List<FileDTO> files;

    @QueryProjection
    public LikeDTO(Long likeId, Long schoolId, Long peopleId, String schoolName, String schoolContent, String schoolAddress, String schoolQr) {
        this.likeId = likeId;
        this.schoolId = schoolId;
        this.peopleId = peopleId;
        this.schoolName = schoolName;
        this.schoolContent = schoolContent;
        this.schoolAddress = schoolAddress;
        this.schoolQr = schoolQr;
    }
}