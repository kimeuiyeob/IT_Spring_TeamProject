package com.app.milestone.repository;

import com.app.milestone.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LikeRepository extends JpaRepository<Like, Long> {
    public Long countBySchoolUserId(Long userId);
    public List<Like> findByPeopleUserId(Long userId);
}
