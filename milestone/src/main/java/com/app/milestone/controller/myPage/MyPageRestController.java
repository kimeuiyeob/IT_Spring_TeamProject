package com.app.milestone.controller.myPage;

import com.app.milestone.domain.*;
import com.app.milestone.service.*;
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
    private final ServiceService serviceService;
    private final TalentService talentService;
    private final LikeService likeService;

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
    private final FileService fileService;

    @PostMapping(value = {"/checkEmail"})
    public Long checkEmail(@RequestBody String userEmail) {
        return userService.checkEmail(userEmail);
    }

    //    휴대폰인증
    @GetMapping("/phoneCheck")
    @ResponseBody
    public String sendSMS(@RequestParam("phone") String userPhoneNumber) { // 휴대폰 문자보내기
        int randomNumber = (int) ((Math.random() * (9999 - 1000 + 1)) + 1000);//난수 생성

//        문자 발송
        certificationService.certifiedPhoneNumber(userPhoneNumber, randomNumber);

        return Integer.toString(randomNumber);
    }

    //    프로필 등록
    @PostMapping("/profile")
    public void profile(HttpSession session, @RequestBody FileDTO fileDTO) {
        Long userId = (Long) session.getAttribute("userId");
        log.info("========================" + fileDTO);
        fileService.register(userId, fileDTO);
    }

    //    비밀번호 변경(비밀번호 확인)
    @GetMapping("/checkPassword")
    public boolean checkPassword(HttpSession session, @RequestParam String userPassword) {
        log.info("checkPassword 진입");
        Long userId = (Long) session.getAttribute("userId");
        log.info("유저번호 : " + userId);
        log.info("비밀번호 : " + userPassword);
        return userService.checkPassword(userId, userPassword);
    }

    //  일반회원 일정 조회
    @GetMapping(value= {"/service/{page}", "/service/{page}/{keyword}"})
    public ServiceResp serviceList(@PathVariable("page") Integer page, @PathVariable(required = false)String keyword) {
        if(keyword == null){keyword= "";}
        ServiceResp serviceResp = new ServiceResp();
        Page<ServiceDTO> arServiceDTO = serviceService.serviceListSearch(page, keyword);
        serviceResp.setArServiceDTO(arServiceDTO);
        return serviceResp;
    }

    //    찜한 보육원
    @GetMapping(value = {"/likeList/{page}"})
    public LikeResp likeSchool(HttpSession session, @PathVariable("page") Integer page) {
        Long userId = (Long) session.getAttribute("userId");

        LikeResp likeResp = new LikeResp();
        Page<LikeDTO> arLikeDTO = likeService.likedSchools(page, userId);
        likeResp.setArLikeDTO(arLikeDTO);

        return likeResp;
    }

    //    좋아요 개수
    @GetMapping(value = {"/likeCount/{userId}"})
    public Long likeCount(@PathVariable("userId") Long userId) {
        return likeService.likeCount(userId);
    }

    //    좋아요 누름
    @GetMapping(value = {"/like/{userId}"})
    public void like(HttpServletRequest request, @PathVariable("userId") Long userId) {
        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("userId");
        likeService.likeSchool(userId, sessionId);
    }

    //    좋아요 취소
    @GetMapping(value = {"/cancel/{userId}"})
    public void cancel(HttpServletRequest request, @PathVariable("userId") Long userId) {
        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("userId");
        likeService.cancelLikeSchool(userId, sessionId);
    }
}
