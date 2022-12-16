package com.app.milestone.repository;

import com.app.milestone.domain.ServiceDTO;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceCustomRepository {
    //    ===============황지수===============
    public List<Tuple> sortByVisitRank();
    public Long checkOverlap(Long userId, ServiceDTO serviceDTO);
    //    ===============황지수===============
    //    개인 일정 조회
    public List<ServiceDTO> findService(Pageable pageable, Long peopleId);
    public List<ServiceDTO> findVisitDate(Long peopleId);
//    보육원 일정 조회
    public List<ServiceDTO> findService1(Pageable pageable, Long schoolId);
    public List<ServiceDTO> findVisitDate1(Long schoolId);

    public Long countByCreatedDate(Pageable pageable, Long peopleId);
    public Long countByCreatedDate1(Pageable pageable, Long schoolId);

    public List<ServiceDTO> findServiceSearch(Pageable pageable, String keyword);

    public List<ServiceDTO> findServiceSearchAsc(Pageable pageable, String keyword);

    public Long countByCreatedDate(Pageable pageable, String keyword);

}
