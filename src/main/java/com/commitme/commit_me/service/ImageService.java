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

    private final ImageRepository imageRepository;
    private final String FOLDER_PATH = System.getProperty("user.dir") + File.separator + "ImagesUpload";

    public String uploadImage(MultipartFile file) throws IOException {
        File directory = new File(FOLDER_PATH);
        if (!directory.exists()) {
            directory.mkdirs(); 
        }
        String filePath = FOLDER_PATH + File.separator + file.getOriginalFilename();
        Image image = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build());
        file.transferTo(new File(filePath));
        if (image != null) {
            return "Archivo subido correctamente!";
        }
        return "Error al cargar el Archivo";
    }

    public byte[] dowloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Image> image = imageRepository.findByName(fileName);
        if (image.isPresent()) {
            String filePath = image.get().getFilePath();
            return Files.readAllBytes(new File(filePath).toPath());
        } else {
            throw new IOException("Imagen no encontrada: " + fileName);
        }

    }

}
