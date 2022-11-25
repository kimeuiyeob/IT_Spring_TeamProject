package com.app.milestone.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_FILE")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends Period{
    @Id
    @GeneratedValue
    private Long fileID;
    private String fileName;
    private String filePath;
    private String fileUuid;
    private int fileSize;
    private boolean fileImageCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private School school;

    @Builder
    public File(String fileName, String filePath, String fileUuid, int fileSize, boolean fileImageCheck) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileImageCheck = fileImageCheck;
    }
}
