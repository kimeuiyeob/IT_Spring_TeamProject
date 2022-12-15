package com.app.milestone.repository;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.domain.QFileDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.milestone.entity.QFile.file;

@Repository
@RequiredArgsConstructor
public class FileCustomRepositoryImpl implements FileCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    //    프로필 조회
    public FileDTO findProfileByUserId(Long userId) {
        return jpaQueryFactory.select(new QFileDTO(
                file.fileName,
                file.filePath,
                file.fileUuid,
                file.fileSize,
                file.fileImageCheck,
                file.fileType,
                file.fileID
        )).from(file)
                .where(
                        file.user.userId.eq(userId).and(file.fileType.eq("profile"))
                )
                .fetchOne();
    }

    //    사진 전체 조회
    public List<FileDTO> findByUserId(Long userId) {
        return jpaQueryFactory.select(new QFileDTO(
                file.fileName,
                file.filePath,
                file.fileUuid,
                file.fileSize,
                file.fileImageCheck,
                file.fileType,
                file.fileID
        )).from(file)
                .where(
                        file.user.userId.eq(userId).and(file.fileType.eq("schoolImg"))
                )
                .fetch();
    }
}
