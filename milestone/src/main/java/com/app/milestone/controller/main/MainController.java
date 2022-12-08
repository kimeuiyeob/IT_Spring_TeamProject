package com.app.milestone.controller.main;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.entity.Money;
import com.app.milestone.entity.People;
import com.app.milestone.entity.Talent;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main/*")
public class MainController {
    private final SchoolService schoolService;
    private final PeopleService peopleService;
    private final MoneyService moneyService;
    private final TalentService talentService;
    private final ServiceService serviceService;

    @PostMapping("/main/people")
    public String createPeople(@Valid PeopleDTO peopleDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "join/joinUser";
        }
        peopleService.createPeople(peopleDTO);

        return "redirect:/main/main";
    }
    @PostMapping("/main/school")
    public String createSchool(@Valid SchoolDTO schoolDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "join/joinSchool";
        }
        schoolService.createSchool(schoolDTO);

        return "redirect:/main/main";
    }

    @PostMapping("/main/OAuth")
    public String createOAuth(@Valid PeopleDTO peopleDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "join/joinOAuth";
        }
        peopleService.createPeople(peopleDTO);

        return "redirect:/main/main";
    }

    @GetMapping("main")
    public void main(Model model) {
//        도움이 필요한 보육원
        model.addAttribute("moneys", peopleService.donationMoneyRanking());
        model.addAttribute("services", peopleService.donationVisitRanking());
        model.addAttribute("talents", peopleService.donationTalentRanking());
        model.addAttribute("schools", schoolService.needHelpList());
    }

}