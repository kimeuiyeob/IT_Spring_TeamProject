package com.app.milestone.controller.ranking;


import com.app.milestone.entity.People;
import com.app.milestone.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ranking/*")
public class RankingController {
    private final PeopleService peopleService;

    @GetMapping("/ranking")
    public void ranking(Model model) {
        model.addAttribute("moneys", peopleService.donationMoneyRanking());
        model.addAttribute("services", peopleService.donationVisitRanking());
        model.addAttribute("talents", peopleService.donationTalentRanking());
    }

//    =========================================================

//    @GetMapping("/ranking")
//    public void ranking(){
//    }
//
//    @GetMapping("/myPageTest1")
//    public void myPageTest1(){
//    }
//
//    @GetMapping("/myPageTest2")
//    public void myPageTest2(){
//    }

}
