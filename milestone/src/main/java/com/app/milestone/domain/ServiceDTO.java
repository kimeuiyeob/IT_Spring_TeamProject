package com.app.milestone.domain;

import com.app.milestone.entity.Service;
import com.app.milestone.entity.Talent;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class ServiceDTO {
    private Long schoolId;
    private Long peopleId;
    private LocalDateTime serviceVisitDate;

    public Service toEntity() {
        return Service.builder()
                .serviceVisitDate(serviceVisitDate)
                .build();
    }

    @QueryProjection
    public ServiceDTO(Long schoolId, Long peopleId, LocalDateTime serviceVisitDate) {
        this.peopleId = peopleId;
        this.schoolId = schoolId;
        this.serviceVisitDate = serviceVisitDate;
    }
}
