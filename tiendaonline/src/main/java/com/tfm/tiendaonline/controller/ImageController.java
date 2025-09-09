package com.tfm.tiendaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tiendaonline.service.ImageUploadService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/media")
public class ImageController {

    @Autowired
    private ImageUploadService imageUploadService;

    @GetMapping("/imagenes")
    public Mono<String> obtenerImagenes() {
        return imageUploadService.getImages();
    }

    @GetMapping("/imagenes/{filename}")
    public Mono<ResponseEntity<byte[]>> getImage(@PathVariable String filename) {
        return imageUploadService.downloadImage(filename)
                .<ResponseEntity<byte[]>>map(imageBytes -> ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, getContentTypeImages(filename.toLowerCase())) // Ajusta el tipo de imagen
                .body(imageBytes))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    private String getContentTypeImages(String filename) {
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (filename.endsWith(".png")) {
            return "image/png";
        } else if (filename.endsWith(".gif")) {
            return "image/gif";
        } else if (filename.endsWith(".bmp")) {
            return "image/bmp";
        } else if (filename.endsWith(".webp")) {
            return "image/webp";
        } else if (filename.endsWith(".tiff") || filename.endsWith(".tif")) {
            return "image/tiff";
        } else {
            return "application/octet-stream";  // Tipo genérico para archivos binarios
        }
    }

}
