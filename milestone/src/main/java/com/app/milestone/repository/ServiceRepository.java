package com.app.milestone.repository;

import com.app.milestone.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ServiceRepository extends JpaRepository<Service, Long>, ServiceCustomRepository {
}
