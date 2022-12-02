package com.app.milestone.repository;

import com.app.milestone.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;


@Component
public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeCustomRepository {

}
