package com.app.milestone.controller.talent;

import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/talent/*")
public class TalentController {

    private final TalentService talentService;

    @GetMapping("/talent")
    public void list(Search search) {
    }

    /*===============================================*/
    //마이페이지 글 삭제 -> 페이지 이동연습 , 레스트로 바꿈, 사용하지 않음!!!
    @GetMapping("/delete")
    public String noticeList(Long donationId){
        talentService.deleteDonationId(donationId);
        return "/mypage/talent";
    }

}
