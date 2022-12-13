package com.app.milestone.repository;

import com.app.milestone.domain.LikeDTO;
import com.app.milestone.domain.Search;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LikeCustomRepository {
    public List<LikeDTO> findSchoolLiked (Pageable pageable, Long peopleId);
    public List<LikeDTO> findSchoolLikedSearch (Pageable pageable, Long peopleId, Search search);
    public Long countByCreatedDate(Pageable pageable, Long peopleId);
    public Long countByCreatedDate2(Pageable pageable, Long peopleId,Search search);

}
