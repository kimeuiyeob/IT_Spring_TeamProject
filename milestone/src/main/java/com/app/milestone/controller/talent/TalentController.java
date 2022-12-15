package com.app.milestone.controller.talent;

import com.app.milestone.service.FileService;
import com.app.milestone.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/talent/*")
public class TalentController {

    private final TalentService talentService;
    private final FileService fileService;

    @GetMapping("/talent")
    public String talentlist(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        log.info("세션값 잘갖고왔니?" + userId);

        if(userId != null){
            model.addAttribute("picture","mypicture");
            model.addAttribute("talentPicture", fileService.showProfile(userId));
        }
        return "/talent/talent";
    }

    /*===============================================*/
    //마이페이지 글 삭제 -> 페이지 이동연습 , 레스트로 바꿈, 사용하지 않음!!!
    @GetMapping("/delete")
    public String noticeList(Long donationId){
        talentService.deleteDonationId(donationId);
        return "/mypage/talent";
    }

}
