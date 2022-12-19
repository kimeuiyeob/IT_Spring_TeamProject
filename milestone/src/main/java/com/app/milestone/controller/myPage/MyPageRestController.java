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
    private final AlarmService alarmService;

    /*=============================================================================*/
    //마이페이지 재능기부 목록 수정
    @GetMapping(value = {"/list/{page}"})
    public TalentResp search(@PathVariable("page") Integer page, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        TalentResp talentResp = new TalentResp();

        Page<TalentDTO> arTalentDTO = talentService.findAllTalentById(page, userId);

        talentResp.setArTalentDTO(arTalentDTO);
        return talentResp;
    }
    /*=============================================================================*/

    /*황지수*/
    private final SchoolService schoolService;
    private final UserService userService;
    private final CertificationService certificationService;
    private final FileService fileService;

//    이메일 중복검사
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

    //    찜한 보육원 목록
    @GetMapping(value = {"/likeList/{page}"})
    public LikeResp likeSchool(HttpSession session, @PathVariable("page") Integer page) {
        Long userId = (Long) session.getAttribute("userId");
        LikeResp likeResp = new LikeResp();
        Page<LikeDTO> arLikeDTO = likeService.likedSchools(page, userId);
        likeResp.setArLikeDTO(arLikeDTO);
        return likeResp;
    }

    //    찜한 보육원 검색
    @GetMapping(value = {"/likeListSearch/{page}", "/likeListSearch/{page}/{schoolAddress}", "/likeListSearch/{page}/{schoolAddress}/{schoolName}"})
    public LikeResp likeSchoolSearch(HttpSession session, @PathVariable("page") Integer page, Search search) {
        Long userId = (Long) session.getAttribute("userId");
        LikeResp likeResp = new LikeResp();
        Page<LikeDTO> arLikeDTO = likeService.likedSchoolsSearch(page, userId, search);
        likeResp.setArLikeDTO(arLikeDTO);
        return likeResp;
    }

    //    찜한 보육원 취소
    @GetMapping(value = {"/cancel/{likeId}"})
    public void cancel(@PathVariable("likeId") Long likeId) {
        likeService.deleteByLikeId(likeId);
    }

    //    좋아요 개수
    @GetMapping(value = {"/likeCount"})
    public Long likeCount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("userId");
        return likeService.likeCountMyPage(sessionId);
    }

    //    개인 일정 조회
    @GetMapping(value = {"/peopleSchedule/{page}"})
    public ServiceResp peopleScheduleList(HttpSession session, @PathVariable("page") Integer page) {
        Long userId = (Long) session.getAttribute("userId");

        ServiceResp serviceResp = new ServiceResp();
        Page<ServiceDTO> arServiceDTO = serviceService.peopleScheduleList(page, userId);
        serviceResp.setArServiceDTO(arServiceDTO);

        return serviceResp;
    }

    //    일정 취소
    @GetMapping(value = {"/serviceDelete/{donationId}"})
    public void serviceDelete(@PathVariable("donationId") Long donationId) {
        serviceService.deleteByDonationId(donationId);
    }

    //    보육원 일정 조회
    @GetMapping(value = {"/schoolSchedule/{page}"})
    public ServiceResp schoolScheduleList(HttpSession session, @PathVariable("page") Integer page) {
        Long userId = (Long) session.getAttribute("userId");

        ServiceResp serviceResp = new ServiceResp();
        Page<ServiceDTO> arServiceDTO = serviceService.schoolScheduleList(page, userId);
        serviceResp.setArServiceDTO(arServiceDTO);

        return serviceResp;
    }


    //    개인 알람 조회
    @GetMapping(value = {"/peopleAlarm/{page}"})
    public AlarmResp peopleAlarmList(HttpSession session, @PathVariable("page") Integer page) {
        Long userId = (Long) session.getAttribute("userId");

        log.info("====================================================");
        log.info("======================들어옴=======================");
        AlarmResp alarmResp = new AlarmResp();
        Page<AlarmDTO> arAlarmDTO = alarmService.peopleAlarmList(userId, page);
        alarmResp.setArAlarmDTO(arAlarmDTO);

        return alarmResp;
    }

    //    알람 확인
    @GetMapping(value = {"/checkAlarm/{alarmId}"})
    public void alarmCheck(@PathVariable("alarmId") Long alarmId) {
        log.info("나눌렀음?");
        alarmService.updateAlarmCheck(alarmId);
    }

    //    보육원 알람 조회
    @GetMapping(value = {"/schoolAlarm/{page}"})
    public AlarmResp schoolAlarmList(HttpSession session, @PathVariable("page") Integer page) {
        Long userId = (Long) session.getAttribute("userId");

        log.info("====================================================");
        log.info("======================들어옴=======================");
        AlarmResp alarmResp = new AlarmResp();
        Page<AlarmDTO> arAlarmDTO = alarmService.schoolAlarmList(userId, page);
        alarmResp.setArAlarmDTO(arAlarmDTO);

        return alarmResp;
    }
}
