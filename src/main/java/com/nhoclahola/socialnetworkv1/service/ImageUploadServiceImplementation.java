package com.nhoclahola.socialnetworkv1.service;

import com.nhoclahola.socialnetworkv1.exception.AppException;
import com.nhoclahola.socialnetworkv1.exception.ErrorCode;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageUploadServiceImplementation implements FileUploadService
{
    private final Tika tika = new Tika();

    private static final String UPLOAD_DIR = "/uploads/";
    private static final String ABSOLUTE_PATH = System.getProperty("user.dir") + UPLOAD_DIR;

    @Override
    public String upload(String path, MultipartFile file) throws IOException
    {
        String absolutePath = ABSOLUTE_PATH + path;
        if (file.isEmpty())
            throw new AppException(ErrorCode.IMAGE_IS_EMPTY);
        String fileType = tika.detect(file.getInputStream());

        if (fileType == null || !fileType.startsWith("image"))
            throw new AppException(ErrorCode.IMAGE_NOT_SUPPORTED);

        // Create new unique image name
        String fileHexName = UUID.randomUUID().toString().replace("-", "");
        String extension = Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = Paths.get(absolutePath, fileHexName + extension).toString();
        File targetFile = new File(filePath);

        // Create folder if it not exist
        if (!targetFile.getParentFile().exists())
            targetFile.getParentFile().mkdirs();
        file.transferTo(targetFile);

        return (UPLOAD_DIR + path + fileHexName + extension).replaceAll("/+", "/");
    }
}
