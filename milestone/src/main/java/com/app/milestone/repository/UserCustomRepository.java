package com.app.milestone.repository;

import com.app.milestone.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserCustomRepository {
    public List<User> findByCreatedDate (Pageable pageable);
}
