package com.app.milestone.domain;

import com.app.milestone.entity.File;
import com.app.milestone.entity.School;
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
                .filePath(filePath)
                .fileUuid(fileUuid)
                .fileSize(fileSize)
                .fileImageCheck(fileImageCheck)
                .build();
    }
}
