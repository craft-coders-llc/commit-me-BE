package com.commitme;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ImageService {

    //     private ImageRepository =repository.save(Image.builder);
    //     public String uploadImage(MultipartFile imagFile) throws IOException {
    //     var imageToSave = image.builder()
    //     .name(imagFile.getOriginalFilename())
    //     .type(imagFile.getContentType())
    //     .imageData(ImageUtils.compressImage(imagFile.getBytes()))
    //     .build();
    //     imageRepository.save(imageToSave);
    //     return "file upload successfully : " + imagFile.getOriginalFilename();
    // }
}
