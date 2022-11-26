package com.app.milestone.domain;

import com.app.milestone.entity.File;
import com.app.milestone.entity.School;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class FileDTO {
    private Long fileID;
    private String fileName;
    private String filePath;
    private String fileUuid;
    private int fileSize;
    private boolean fileImageCheck;
    private SchoolDTO schoolDTO;

    public File toEntity() {
        return File.builder()
                .fileName(fileName)
                .build();
    }

    @QueryProjection
    public FileDTO(Long fileID, String fileName, String filePath, String fileUuid, int fileSize, boolean fileImageCheck, SchoolDTO schoolDTO) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileImageCheck = fileImageCheck;
        this.schoolDTO = schoolDTO;
    }
}
