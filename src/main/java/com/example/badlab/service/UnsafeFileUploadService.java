package com.example.badlab.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.springframework.stereotype.Service;

@Service
public class UnsafeFileUploadService {
    public void saveUpload(String fileName, byte[] content) throws Exception {
        Path uploadPath = Path.of("uploads", fileName);
        Files.createDirectories(uploadPath.getParent());
        Files.write(uploadPath, content);
    }

    public void unzipToDirectory(ZipInputStream zipStream, String destination) throws Exception {
        ZipEntry entry;
        while ((entry = zipStream.getNextEntry()) != null) {
            File output = new File(destination, entry.getName());
            if (entry.isDirectory()) {
                output.mkdirs();
            } else {
                output.getParentFile().mkdirs();
                Files.copy(zipStream, output.toPath());
            }
        }
    }
}
