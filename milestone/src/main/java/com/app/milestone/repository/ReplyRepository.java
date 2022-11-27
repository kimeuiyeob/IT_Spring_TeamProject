package com.app.milestone.repository;

import com.app.milestone.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
