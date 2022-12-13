package com.app.milestone.repository;

import com.app.milestone.domain.LikeDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LikeCustomRepository {
    public List<LikeDTO> findSchoolLiked (Pageable pageable, Long peopleId);
    public Long countByCreatedDate(Pageable pageable, Long peopleId);

}
