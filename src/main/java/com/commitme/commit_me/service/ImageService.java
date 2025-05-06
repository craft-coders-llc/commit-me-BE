package com.commitme.commit_me.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.commitme.commit_me.model.Image;
import com.commitme.commit_me.repository.ImageRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ImageService {

        private ImageRepository imageRepository;
        private final String FOLDER_PATH = "C:\\Users\\ximen\\Desktop\\proyectosFactoria\\commit-me-BE\\ImagesUpload";
 
        public String uploadImage(MultipartFile file) throws IOException {
            String filePath = FOLDER_PATH + file.getOriginalFilename();
            Image image = imageRepository.save(Image.builder()
            .name(file.getOriginalFilename())
            .type(file.getContentType())
            .filePath(filePath).build()
            );
            file.transferTo(new File(filePath));
            if (image != null) {
                return "file uploaded successfully";
            }
            return null;
    }
        
        public byte [] dowloadImageFromFileSystem(String fileName) throws IOException {
            Optional<Image> image = imageRepository.findByName(fileName);
            String filePath = image.get().getFilePath();
            byte[] images =Files.readAllBytes(new File(filePath).toPath());
            return images;
        }
    
}
