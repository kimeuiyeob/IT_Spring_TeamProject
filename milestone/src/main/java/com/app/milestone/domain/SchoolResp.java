package com.app.milestone.domain;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class SchoolResp {
    private Long total;
    private Page<SchoolDTO> arSchoolDTO;
}
