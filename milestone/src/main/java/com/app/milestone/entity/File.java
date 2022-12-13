package com.app.milestone.entity;

import com.app.milestone.type.FileType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_FILE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends Period {
    @Id
    @GeneratedValue
    private Long fileID;
    @NotNull
    private String fileName;
    @NotNull
    private String filePath;
    @NotNull
    private String fileUuid;
    @NotNull
    private Long fileSize;
    private boolean fileImageCheck;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private FileType fileType;

    //
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID")
//    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @NotNull
    private User user;

    @Builder
    public File(String fileName, String filePath, String fileUuid, Long fileSize, boolean fileImageCheck, FileType fileType) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileUuid = fileUuid;
        this.fileSize = fileSize;
        this.fileImageCheck = fileImageCheck;
        this.fileType = fileType;
    }

    public void changeUser(User user){
        this.user = user;
//        user.getFiles().add(this);
    }

    public void update(Boolean fileImageCheck) {
        this.fileImageCheck = fileImageCheck;
    }
//    @Builder
//    public File(String fileName, String filePath, String fileUuid, Long fileSize, boolean fileImageCheck, School school, People people) {
//        this.fileName = fileName;
//        this.filePath = filePath;
//        this.fileUuid = fileUuid;
//        this.fileSize = fileSize;
//        this.fileImageCheck = fileImageCheck;
//        this.school = school;
//        this.people = people;
//    }
}
