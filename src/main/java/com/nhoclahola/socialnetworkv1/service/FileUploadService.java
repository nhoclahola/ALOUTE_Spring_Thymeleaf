package com.nhoclahola.socialnetworkv1.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService
{
    public abstract String upload(String path, MultipartFile file) throws IOException;
}
