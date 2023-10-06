package com.example.demo.controllers;

// контроллер который получает фото из БД, преобразовывает байты и расдаёт

import com.example.demo.models.Image;
import com.example.demo.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController // не возвращает представление
@RequiredArgsConstructor
@Component
public class ImageController {

    private ImageRepository imageRepository;

    @Autowired
    @GetMapping("/images/{id}") // принимает айди картинки
    private ResponseEntity <?> getImageById(@PathVariable Long id) {
        Image image = imageRepository.findBy(id).orElse(null);
        return ResponseEntity.ok().header("fileName,", image.getOriginalFileName()) // имя картинке
                .contentType(MediaType.valueOf(image.getContentType())) // преобразовываем картинку
                .contentLength(image.getSize()) // устанавливаем размер картинки
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
