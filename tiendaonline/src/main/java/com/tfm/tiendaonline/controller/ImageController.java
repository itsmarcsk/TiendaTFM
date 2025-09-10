package com.tfm.tiendaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tiendaonline.service.ImageUploadService;

import reactor.core.publisher.Mono;

@RestController // Marca esta clase como un controlador REST en Spring
@RequestMapping("/media") // Prefijo para todas las rutas de este controlador
public class ImageController {

    @Autowired
    private ImageUploadService imageUploadService; // Servicio que gestiona la interacción con la API de imágenes

    // -------------------------------
    // Endpoint para obtener la lista de imágenes
    // -------------------------------
    @GetMapping("/imagenes")
    public Mono<String> obtenerImagenes() {
        // Llama al servicio reactivo para obtener la lista de imágenes
        return imageUploadService.getImages();
    }

    // -------------------------------
    // Endpoint para descargar una imagen específica por su nombre de archivo
    // -------------------------------
    @GetMapping("/imagenes/{filename}")
    public Mono<ResponseEntity<byte[]>> getImage(@PathVariable String filename) {
        // Llama al servicio para descargar la imagen como un arreglo de bytes
        return imageUploadService.downloadImage(filename)
                .<ResponseEntity<byte[]>>map(imageBytes -> ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, getContentTypeImages(filename.toLowerCase())) // Ajusta el tipo MIME según la extensión
                .body(imageBytes)) // Cuerpo de la respuesta con la imagen
                .defaultIfEmpty(ResponseEntity.notFound().build()); // Si no se encuentra la imagen, devuelve 404
    }

    // -------------------------------
    // Método privado para determinar el tipo MIME según la extensión del archivo
    // -------------------------------
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
            return "application/octet-stream";  // Tipo genérico para archivos binarios desconocidos
        }
    }

}
