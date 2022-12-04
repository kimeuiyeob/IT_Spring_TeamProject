package com.app.milestone.controller.main;

import com.app.milestone.entity.Money;
import com.app.milestone.entity.People;
import com.app.milestone.entity.Talent;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main/*")
public class MainController {
    private final SchoolService schoolService;
    private final PeopleService peopleService;
    private final MoneyService moneyService;
    private final TalentService talentService;
    private final ServiceService serviceService;

    @GetMapping("main")
    public void main(Model model) {
//        도움이 필요한 보육원
        model.addAttribute("moneys", peopleService.donationMoneyRanking());
        model.addAttribute("services", peopleService.donationVisitRanking());
        model.addAttribute("talents", peopleService.donationTalentRanking());
        model.addAttribute("schools", schoolService.needHelpList());
    }

}