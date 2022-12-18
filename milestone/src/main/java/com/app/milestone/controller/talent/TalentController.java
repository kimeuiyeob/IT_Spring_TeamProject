package com.app.milestone.controller.talent;

import com.app.milestone.domain.FileDTO;
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

    /*========================================김의엽======================================*/
    //제일 첫 화면 재능기부 페이지 경로
    @GetMapping("/talent")
    public String talentlist(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        FileDTO fileDTO = null;

        //세션아이디가 있을때는 showProfile을 통해 해당 user의 프로필사진을 불러온다.
        if(userId != null){
            model.addAttribute("picture","mypicture");
            fileDTO = fileService.showProfile(userId);

            if(fileDTO != null) {
                model.addAttribute("talentPicture", fileDTO);
            }
        }
        return "/talent/talent";
    }

    /*========================================김의엽======================================*/
    //마이페이지 글 삭제 -> 페이지 이동연습 , 레스트로 바꿈, 사용하지 않음!!!
    @GetMapping("/delete")
    public String noticeList(Long donationId){
        talentService.deleteDonationId(donationId);
        return "/mypage/talent";
    }

}
