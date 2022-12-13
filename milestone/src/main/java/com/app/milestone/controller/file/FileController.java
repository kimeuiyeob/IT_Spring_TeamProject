package com.app.milestone.controller.file;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.service.FileService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file/*")
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload")
    public List<FileDTO> upload(List<MultipartFile> upload) throws IOException {
        String rootPath = "C:/upload";
        String uploadFileName = null;
        List<FileDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : upload) {
            FileDTO FileDTO = new FileDTO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;
            FileDTO.setFileName(fileName);
            FileDTO.setFileUuid(uuid.toString());
            FileDTO.setFilePath(createDirectoryByNow());
            FileDTO.setFileSize(multipartFile.getSize());

            File saveFile = new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            if (checkImageType(saveFile)) {
                FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
                Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
                thumbnail.close();
                FileDTO.setFileImageCheck(true);
            }
            files.add(FileDTO);
        }
        return files;
    }

    @GetMapping("/display")
    public byte[] display(String fileName) throws IOException {
        File file = new File("C:/upload", fileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    @PostMapping("/delete")
    public void delete(List<String> uploadPath, List<String> fileName) {
        for (int i = 0; i < uploadPath.size(); i++){
            File file = new File("C:/upload", uploadPath.get(i) + "/" + fileName.get(i));
            if (file.exists()) {
                file.delete();
            }

            file = new File("C:/upload", uploadPath.get(i) + "/s_" + fileName.get(i));
            if (file.exists()) {
                file.delete();
            }
        }

    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(String fileName) throws UnsupportedEncodingException {
        Resource resource = new FileSystemResource("C:/upload/" + fileName);
        HttpHeaders header = new HttpHeaders();
        String name = resource.getFilename();
        name = name.substring(name.indexOf("_") + 1);
        header.add("Content-Disposition", "attachment;filename=" + new String(name.getBytes("UTF-8")));
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }

    @GetMapping("/schoolImg")
    public List<FileDTO> schoolImg(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        return fileService.showAll(userId);
    }

    public boolean checkImageType(File file) throws IOException {
        return Files.probeContentType(file.toPath()).startsWith("image");
    }

    public String createDirectoryByNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }
}
