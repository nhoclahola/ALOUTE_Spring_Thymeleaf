package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.exception.AppException;
import com.nhoclahola.socialnetworkv1.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class VideoUploadServiceImplementation implements FileUploadService
{
    @Override
    public String upload(String userId, String path, MultipartFile file) throws IOException
    {
        if (file.isEmpty())
            throw new AppException(ErrorCode.VIDEO_IS_EMPTY);
        String fileType = file.getContentType();
        String fileFolderPath;
        if (fileType != null && fileType.startsWith("video"))
            fileFolderPath = path + userId + "/videos/";
        else
            throw new AppException(ErrorCode.VIDEO_NOT_SUPPORTED);

        // Create new unique image name
        String fileHexName = UUID.randomUUID().toString().replace("-", "");
        String extension = Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = Paths.get(fileFolderPath, fileHexName + extension).toString();
        File targetFile = new File(filePath);

        // Create folder if it not exist
        if (!targetFile.getParentFile().exists())
            targetFile.getParentFile().mkdirs();
        file.transferTo(targetFile);

        return "http://localhost:8080/uploads/posts/" + userId + "/videos/" + fileHexName + extension;
    }
}
