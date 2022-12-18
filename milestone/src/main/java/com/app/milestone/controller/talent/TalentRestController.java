package com.app.milestone.controller.talent;


import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.domain.TalentResp;
import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.app.milestone.entity.Talent;
import com.app.milestone.repository.DonationRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.service.SchoolService;
import com.app.milestone.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/talentrest/*")
@Slf4j
public class TalentRestController {

    private final TalentService talentService;
    private final SchoolService schoolService;
    private final DonationRepository donationRepository;
    private final PeopleRepository peopleRepository;

    /*===============================================*/
    //재능 기부 목록
    @GetMapping(value = {"/list/{page}", "/list/{page}/{talentCategory}", "/list/{page}/{talentCategory}/{talentPlace}", "/list/{page}/{talentCategory}/{talentPlace}/{talentTitle}"})
    public TalentResp search(@PathVariable("page") Integer page, Search search) {

        TalentResp talentResp = new TalentResp();
        Page<TalentDTO> arTalentDTO = talentService.talentList(page, search);
        talentResp.setArTalentDTO(arTalentDTO);

        return talentResp;
    }

    /*===============================================*/
    //재능기부 목록 상세페이지
    @GetMapping(value = {"/showmodal/{donationId}"})
    public List<TalentDTO> showDetail(@PathVariable("donationId") Long donationId) { //@PathVariable 이걸로 같은 이름을 찾아온다.
        return talentService.talentDetail(donationId);
    }

    /*===============================================*/
    //재능기부 글 작성시 저장
    @PostMapping("/write")
    public void saveWrite(@RequestBody TalentDTO talentDTO, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        talentDTO.setPeopleUserId(userId);
        talentService.registerTalent(talentDTO);
    }

    /*===============================================*/
    //보육원 => 신청하기 저장
    @PostMapping("/sign")
    @Transactional
//                          화면에서 받아온 해당 도네이션 ID, 세션을 불러올 HttpServletRequest
    public void signWrite(@RequestBody Long donationId, HttpServletRequest request) {
//      HttpSession타입 / session으로 저장 / request.getSession()으로 세션값을 받아온다.
        HttpSession session = request.getSession();
//      Long타입/ userId로 저장 / session의 getAttribute통해 이름을 userId로 해준다.
        Long userId = (Long) session.getAttribute("userId");

//      TalentDTO타입 / talentDTO저장 /  talentService안에 있는 findByDonationId(donationId)해당 도네이션아이디로 talentDTO를 불러온다.
        TalentDTO talentDTO = talentService.findByDonationId(donationId);

//      School타입 / school저장 / schoolService안에 있는 selectSchoolId(userId)세션아이디로
        School school = schoolService.selectSchoolId(userId);

//      Talent타입 / talent저장 / schoolService안에 있는 selectDonation(talentDTO)통해 해당 talentDTO를 불러온다.
        Talent talent = talentService.selectDonation(talentDTO);

//      talent.changeSchool로 school저장
        talent.changeSchool(school);

//      talentDTO안에있는 schoolUserId를 세셔아이디로 바까준다.
        talentDTO.setSchoolUserId(userId);

//      talentService에 있는 signTalentPeople(talentDTO)통해 해당 talent수정
        talentService.signTalentPeople(talentDTO);

        //-------------------------------------------------------------------
        People people = peopleRepository.findById(talentDTO.getPeopleUserId()).get();

        int donationCount = 0;
        donationCount = donationRepository.countByPeopleUserId(people.getUserId());
        people.update(donationCount);
        donationCount = donationRepository.countBySchoolUserId(userId);
        school.update(donationCount);
        //-------------------------------------------------------------------
    }

    /*===============================================*/
    //***마이페이지*** 재능기부 목록 글 수정
    @PostMapping("/change")
    public void changeWrite(@RequestBody TalentDTO talentDTO) {
        log.info("탤런트 디티오 가져왔어요 : " + talentDTO);
        talentService.changeWriteMypage(talentDTO);
    }

    /*===============================================*/
    //***마이페이지*** 재능기부 목록 글 삭제
    @PostMapping("/delete")
    public void deleteWrite(@RequestBody TalentDTO talentDTO) {
        talentService.deleteByDonationId(talentDTO.getDonationId());
    }


}