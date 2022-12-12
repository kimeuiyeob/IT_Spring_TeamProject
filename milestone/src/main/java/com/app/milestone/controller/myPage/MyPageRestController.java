package com.app.milestone.controller.myPage;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.domain.TalentResp;
import com.app.milestone.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myPageRest/*")
@Slf4j
public class MyPageRestController {

    private final TalentService talentService;

    /*=============================================================================*/
    //마이페이지 재능기부 목록 수정
    @GetMapping(value = {"/list/{page}"})
    public TalentResp search(@PathVariable("page") Integer page) {
        TalentResp talentResp = new TalentResp();

        log.info("마이페이지 레스트 컨트롤러에 도착했습니다!!!");

        Page<TalentDTO> arTalentDTO = talentService.findAllTalentById(page,101L); // <=================== 세션값 들어가야됩니다.

        talentResp.setArTalentDTO(arTalentDTO);
        return talentResp;
    }
    /*=============================================================================*/
    
    /*황지수*/
    @PostMapping(value = "/register")
    public void register(@RequestBody SchoolDTO schoolDTO){
        log.info("=============asdfas============="+schoolDTO);
    }

}
