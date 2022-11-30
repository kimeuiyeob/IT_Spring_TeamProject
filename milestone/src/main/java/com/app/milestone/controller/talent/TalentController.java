package com.app.milestone.controller.talent;

import com.app.milestone.domain.TalentDTO;
import com.app.milestone.entity.QTalent;
import org.hibernate.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/talent/*")
public class TalentController {

    @GetMapping("/talent")
    public void list(Model model){
        TalentDTO talentDTO = new TalentDTO();
        model.addAttribute("talents", talentDTO);
    }
}
