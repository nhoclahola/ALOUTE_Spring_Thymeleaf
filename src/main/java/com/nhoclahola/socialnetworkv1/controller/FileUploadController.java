package com.nhoclahola.socialnetworkv1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FileUploadController
{
    // Đường dẫn tuyệt đối đến thư mục uploads trong thư mục gốc của dự án
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Xác định đường dẫn đầy đủ đến tệp
            String filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename()).toString();
            File targetFile = new File(filePath);

            // Tạo thư mục nếu chưa tồn tại
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }

            // Lưu tệp vào đĩa
            file.transferTo(targetFile);

            // Xây dựng URL đầy đủ để trả về
            String fileDownloadUri = "http://localhost:8080/uploads/" + file.getOriginalFilename();

            return "File uploaded successfully: " + fileDownloadUri;
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed: " + e.getMessage();
        }
    }
}
