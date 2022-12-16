package com.app.milestone.service;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.domain.Ranking;
import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.entity.Talent;
import com.app.milestone.repository.*;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TalentService {

    private final TalentRepository talentRepository;
    private final PeopleRepository peopleRepository;
    private final FileRepository fileRepository;
    private final DonationService donationService;
    
    
    //재능기부 목록 조회
    //페이지객체는 list가 가지고있지 않은 realEnd(마지막페이지) total구할수있다(총갯수)
    //현재 페이지가 첫페이지인지 마지막 페이지인지 boolean으로 리턴을 하는 메소드가 있다.
    //.pageNumber : 현재페이지 / .totalPage : 마지막페이지 <-이런것들을 쓰려고 페이지타입으로 쓴거다.
    //     Page타입         talentList가  page(현재보고자하는페이지)  search를 받아온다. Search타입으로 받아온이유는 Search에 있는 여러필드를 쓰겠다.

    public Page<TalentDTO> talentList(Integer page, Search search) {

        //  페이지가 null <-들어가자마자는 페이지를 넘겨주지 않았으니까 페이지에 0을 전달한다. -> 이게 1페이지
        if (page == null) page = 0;  //if문 {}한줄일때는 생략가능
        //페이지와 size10을 한꺼번에 쓰려고 pageable에 담아준다.
        //PageRequest이게 사용자가 요청한거 page와 size10(한번에 보여질 게시글의 개수)
        Pageable pageable = PageRequest.of(page, 10);

        if (search.getTalentPlace() == null) {
            search.setTalentPlace(new ArrayList<>());
        }
        //List타입으로  list에담는다  talentRepository에있는 findAllByTalentAbleDate 메소드로 pageable의 값과 search값을 보낸다.
        List<TalentDTO> list = talentRepository.findAllByTalentAbleDate(pageable, search);

        //Page타입     talents담는다.  페이지구현체  사용자가 요청한 10개 게시글 / 자를때사용한도구 / 앞에 사용자가 요청한 게시글을 자르기 위해서 총 게시글의 개수
        Page<TalentDTO> talents = new PageImpl<>(list, pageable, Integer.valueOf("" + talentRepository.countByAbleDate3(pageable, search)));
        //리턴 talents
        return talents;
    }

    /*=================================================================*/
    //재능기부 글 등록
    public void registerTalent(TalentDTO talentDTO) { //talentDTO를 받아와서
        Talent entity = talentDTO.toEntity(); //talent 엔티티화하고 entity에 담아준다.
        entity.changePeople(peopleRepository.findById(talentDTO.getPeopleUserId()).get());
        talentRepository.save(entity);
    }

    /*=================================================================*/
    //재능 기부 목록 상세보기
    public List<TalentDTO> talentDetail(Long donationId) {
        return talentRepository.talentDetail(donationId);
    }

    /*=================================================================*/

    //    ===================================기부 랭킹====================================
    //    재능기부 횟수 랭킹
    public List<Ranking> donationTalentRanking() {
        List<Ranking> arRanking = new ArrayList<>();
        List<Tuple> rankingInfo = talentRepository.sortBytalentRank();
        for (Tuple tuple : rankingInfo) {
            Ranking ranking = new Ranking();
            String peopleNickname = peopleRepository.findById(tuple.get(1, Long.TYPE)).get().getPeopleNickname();
            FileDTO fileDTO = fileRepository.findProfileByUserId(tuple.get(1, Long.TYPE));
            ranking.setFile(fileDTO);
            ranking.setPeopleNickname(peopleNickname);
            ranking.setUserId(tuple.get(1, Long.TYPE));
            ranking.setRankingItem(tuple.get(0, Long.TYPE));
            arRanking.add(ranking);
        }
        return arRanking;
    }

    public Page<TalentDTO> searchedTalentList(Integer page, Search search) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (search.getKeyword() == null) {
            search.setKeyword(null);
        }
        if (search.getTalentCategory() == null) {
            search.setTalentCategory(null);
        }
        List<TalentDTO> list = talentRepository.findTalentSearch(pageable, search);
        Page<TalentDTO> talents = new PageImpl<>(list, pageable, Integer.valueOf("" + talentRepository.countByCreatedDate(pageable, search)));

        return talents;
    }
    /*====================================================================================================================*/
    //  재능기부 신청하기 -> 해당 도네이션 아이디로
    public TalentDTO findByDonationId(Long donationId) {
        return talentRepository.findByDonationId(donationId).get(0);
    }

    /*========================================================================*/
    //  재능기부 삭제
    public void deleteByDonationId(Long donationId) {
        talentRepository.deleteById(donationId);
    }

    /*========================================================================*/
    //마이페이지 재능기부 목록
    public Page<TalentDTO> findAllTalentById(Integer page, Long peopleId) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 5);
        List<TalentDTO> list = talentRepository.findAllTalentById(pageable, peopleId);
        Page<TalentDTO> talents = new PageImpl<>(list, pageable, Integer.valueOf("" + talentRepository.countByAbleDate2(pageable, peopleId)));

        return talents;
    }

    /*========================================================================*/
    //마이페이지 재능기부 -> 해당 도네이션 아이디로 삭제
    public void deleteDonationId(Long donationId) {
        talentRepository.deleteAllById(Collections.singleton(donationId));
    }

    /*========================================================================*/
    //마이페이지 재능기부 -> 해당 도네이션 수정
    @Transactional
    public void signTalentPeople(TalentDTO talentDTO) {
        talentRepository.findById(talentDTO.getDonationId()).get().update(talentDTO);
        donationService.alarm(talentDTO);
    }

    @Transactional
    public void changeWriteMypage(TalentDTO talentDTO) {
        talentRepository.findById(talentDTO.getDonationId()).get().update(talentDTO);
    }

    //   재능기부 보육원 로그인 => 신청하기
    public Talent selectDonation(TalentDTO talentDTO) {
       return talentRepository.findById(talentDTO.getDonationId()).get();
    }
}
