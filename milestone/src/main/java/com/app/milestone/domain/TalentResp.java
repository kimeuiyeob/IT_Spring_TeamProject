package com.app.milestone.domain;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class TalentResp {
    private Long total;
    private List<TalentDTO> arTalentDTO;
}
