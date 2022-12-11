package com.app.milestone.controller.ranking;


import com.app.milestone.entity.People;
import com.app.milestone.entity.Service;
import com.app.milestone.service.MoneyService;
import com.app.milestone.service.PeopleService;
import com.app.milestone.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ranking/*")
public class RankingController {
    private final MoneyService moneyService;
    private final ServiceService serviceService;
    private final PeopleService peopleService;

    @GetMapping("/ranking")
    public void ranking(Model model) {
        model.addAttribute("moneys", moneyService.donationMoneyRanking());
        model.addAttribute("services", serviceService.donationVisitRanking());
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
