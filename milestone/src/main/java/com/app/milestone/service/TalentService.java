package com.app.milestone.service;

import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.entity.Talent;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.TalentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TalentService {

    private final TalentRepository talentRepository;
    private final PeopleRepository peopleRepository;

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
                search.getTalentPlace().add(null);
            }
            if (search.getTalentTitle() == null) {
                search.setTalentTitle(null);
            }
            //List타입으로  list에담는다  talentRepository에있는 findAllByTalentAbleDate 메소드로 pageable의 값과 search값을 보낸다.
            List<TalentDTO> list = talentRepository.findAllByTalentAbleDate(pageable, search);

            int start = list.size() > (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
            int end = Math.min((start + pageable.getPageSize()), list.size());

            //Page타입     talents담는다.  페이지구현체  사용자가 요청한 10개 게시글 / 자를때사용한도구 / 앞에 사용자가 요청한 게시글을 자르기 위해서 총 게시글의 개수
            Page<TalentDTO> talents = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + talentRepository.countByAbleDate(pageable, search)));
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
    public List<TalentDTO> talentDetail(Long userId) {
        return talentRepository.talentDetail(userId);
    }

    //전체클릭시 전체 조회하기 (가능일자순으로)
    public List<TalentDTO> allList() {
        return talentRepository.allList();
    }

    //교육클릭시 교육 조회하기 (가능일자순으로)
    public List<TalentDTO> educationList() {
        return talentRepository.educationList();
    }

    //운동클릭시 운동 조회하기 (가능일자순으로)
    public List<TalentDTO> exerciseList() {
        return talentRepository.exerciseList();
    }

    //음악클릭시 음악 조회하기 (가능일자순으로)
    public List<TalentDTO> musicList() {
        return talentRepository.musicList();
    }

    //미술클릭시 미술 조회하기 (가능일자순으로)
    public List<TalentDTO> artList() {
        return talentRepository.artList();
    }

    //IT클릭시 IT 조회하기 (가능일자순으로)
    public List<TalentDTO> itList() {
        return talentRepository.itList();
    }


}
