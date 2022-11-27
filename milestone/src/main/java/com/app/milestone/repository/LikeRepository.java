package com.app.milestone.repository;

import com.app.milestone.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface LikeRepository extends JpaRepository<Like, Long> {
}
