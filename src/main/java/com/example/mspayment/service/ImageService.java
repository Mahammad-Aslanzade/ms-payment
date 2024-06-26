package com.example.mspayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final String FOLDER_PATH = System.getProperty("user.dir") + "/uploads/";

    public String upLoadImageAndGetPath(MultipartFile file, String folderName) throws IOException {
        String filePath = FOLDER_PATH + folderName + "/" + file.getOriginalFilename();
        file.transferTo(new File(filePath));
        if (file != null) {
            return filePath;
        }
        return null;
    }


    public String deleteImage(String filePath){
        Path path = Paths.get(filePath);
        try {
            Files.delete(path);
        } catch (IOException e) {
            return "IMAGE_CANNOT_DELETED";
        }
        return "IMAGE_DELETED";
    }


}
