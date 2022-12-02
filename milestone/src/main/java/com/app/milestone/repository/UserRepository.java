package com.app.milestone.repository;

import com.app.milestone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select new com.app.milestone.entity.User(u.userName, u.userEmail, u.userPhoneNumber,u.createdDate, u.) from User u where u.= :userType")
//    public OwnerDTO findByIdToDTO(Long ownerId);
}
