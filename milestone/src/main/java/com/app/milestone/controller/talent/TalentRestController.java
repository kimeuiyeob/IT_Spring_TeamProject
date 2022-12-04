package com.app.milestone.controller.talent;


import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/talentrest/*")
@Slf4j
public class TalentRestController {

    private final TalentService talentService;

    @GetMapping("/list/{talentPlace}")
    public List<TalentDTO> search(Pageable pageable, Search search, Model model) {
        pageable = PageRequest.of(0, 10);
        if (search.getTalentPlace() == null) {
            search.setTalentPlace(new ArrayList<>());
        }
        model.addAttribute("talents", talentService.talentList(pageable, search));
        return talentService.talentList(pageable, search);
    }

    @PostMapping("/showall")
    public List<TalentDTO> showAll() {
        log.info("교육클릭컨트롤러><");
        talentService.educationList().stream().map(o -> o.toString()).forEach(log::info);
       return talentService.educationList();
    }
}
