package com.tfm.tiendaonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ImageUploadService {

    private static final String FASTAPI_URL = "http://api-ubuntu-container:5000";
    
    private final WebClient.Builder webClientBuilder;
    @Autowired
    private RestTemplate restTemplate;

    public ImageUploadService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    // Método para obtener la lista de imágenes desde la API de FastAPI
    public Mono<String> getImages() {
        String endpoint = "/images/";  // El endpoint para obtener las imágenes de FastAPI

        return webClientBuilder.build()
                .get()
                .uri(FASTAPI_URL + endpoint)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.just("No se pudieron obtener las imágenes: " + e.getMessage()));
    }
    
    public Mono<byte[]> downloadImage(String filename) {
        String endpoint = "/download/image/" + filename;  // El endpoint para descargar la imagen

        return webClientBuilder.build()
                .get()
                .uri(FASTAPI_URL + endpoint)
                .retrieve()
                .bodyToMono(byte[].class)  // Retorna la imagen como un arreglo de bytes
                .onErrorResume(e -> Mono.empty());  // Retorna vacío si ocurre un error
    }
    
    

    public ResponseEntity<byte[]> getVideoFromExternalApi(String filename) {
        String videoUrl = FASTAPI_URL + "/download/video/" + filename; // Cambia esto a la URL de tu API

        // Hacer la solicitud HTTP para obtener el video
        return restTemplate.getForEntity(videoUrl, byte[].class);
    }
}
