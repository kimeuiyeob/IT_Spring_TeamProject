package com.app.milestone.controller.talent;

import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.entity.QTalent;
import com.app.milestone.entity.Talent;
import com.app.milestone.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/talent/*")
public class TalentController {

    private final TalentService talentService;

    @GetMapping("/talent")
    public void list(Pageable pageable, Search search, Model model){

        pageable = PageRequest.of(0, 10); //TalentCustomRepositoryImpl에 pageable값을 넘겨주기위해서 생성
        if (search.getTalentPlace() == null) {
            search.setTalentPlace(new ArrayList<>()); //TalentCustomRepositoryImpl에 search값을 넘겨주기위해서 생성
        }
        model.addAttribute("talents", talentService.talentList(pageable,search));
    }

}
