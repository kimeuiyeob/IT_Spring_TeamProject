package com.app.milestone.controller.myPage;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.domain.TalentResp;
import com.app.milestone.service.CertificationService;
import com.app.milestone.service.SchoolService;
import com.app.milestone.service.TalentService;
import com.app.milestone.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

        Page<TalentDTO> arTalentDTO = talentService.findAllTalentById(page, 101L); // <=================== 세션값 들어가야됩니다.

        talentResp.setArTalentDTO(arTalentDTO);
        return talentResp;
    }
    /*=============================================================================*/

    /*황지수*/
    private final SchoolService schoolService;
    private final UserService userService;
    private final CertificationService certificationService;

    @PostMapping(value = {"/checkEmail"})
    public Long checkEmail(@RequestBody String userEmail){
        return userService.checkEmail(userEmail);
    }

//    휴대폰인증
    @GetMapping("/phoneCheck")
    @ResponseBody
    public String sendSMS(@RequestParam("phone") String userPhoneNumber) { // 휴대폰 문자보내기
        int randomNumber = (int) ((Math.random() * (9999 - 1000 + 1)) + 1000);//난수 생성

        certificationService.certifiedPhoneNumber(userPhoneNumber, randomNumber);

        return Integer.toString(randomNumber);
    }

}
