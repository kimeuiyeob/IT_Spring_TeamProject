package com.app.milestone.repository;

import com.app.milestone.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface FileRepository extends JpaRepository<File, Long>, FileCustomRepository {
    @Modifying(clearAutomatically = true)
    @Query("delete from File f where f.user.userId = :userId and f.fileType = 'profile'")
    public void deleteByUserId(@Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Query("delete from File f where f.user.userId = :userId and f.fileType = 'schoolImg'")
    public void deleteSchoolImgByUserId(@Param("userId") Long userId);

    public void deleteByUserUserId(Long userId);
}
