package com.app.milestone.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class SchoolResp {
    private Long total;
    private List<SchoolDTO> arSchoolDTO;
}
