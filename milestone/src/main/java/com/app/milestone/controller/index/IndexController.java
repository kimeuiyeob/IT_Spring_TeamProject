package com.app.milestone.controller.index;

import com.app.milestone.domain.SessionManager;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class IndexController {
    private final SchoolService schoolService;
    private final PeopleService peopleService;
    private final MoneyService moneyService;
    private final TalentService talentService;
    private final ServiceService serviceService;
    private final FileService fileService;
    private final SessionManager sessionManager;

    @GetMapping("")
    public void main(HttpSession session, Model model) {
//        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
//        도움이 필요한 보육원
//        model.addAttribute("fileDTO", fileService.showProfile(userId));
        model.addAttribute("moneys", moneyService.donationMoneyRanking());
        model.addAttribute("services", serviceService.donationVisitRanking());
        model.addAttribute("talents", talentService.donationTalentRanking());
        model.addAttribute("schools", schoolService.needHelpList());
        if (userId != null) {
            model.addAttribute("fileDTO", fileService.showProfile(userId));
        }
    }
}
