package com.app.milestone.repository;

import com.app.milestone.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LikeRepository extends JpaRepository<Like, Long>, LikeCustomRepository {
    public Long countBySchoolUserId(Long userId);
    public Long countByPeopleUserId(Long userId);
    public List<Like> findByPeopleUserId(Long userId);
    @Modifying(clearAutomatically = true)
    @Query("delete from Like l where l.people.userId = :sessionId and l.school.userId = :userId")
    public void deleteByPeopleIdAndSchoolId(Long sessionId,Long userId);
}
