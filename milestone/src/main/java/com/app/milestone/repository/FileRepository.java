package com.app.milestone.repository;

import com.app.milestone.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FileRepository extends JpaRepository<File, Long> {
}
