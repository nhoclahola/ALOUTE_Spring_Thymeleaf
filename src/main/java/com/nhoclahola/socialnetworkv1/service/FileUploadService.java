package com.nhoclahola.socialnetworkv1.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService
{
    public abstract String upload(String userId, String path, MultipartFile file) throws IOException;
}
