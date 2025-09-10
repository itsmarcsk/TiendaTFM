package com.tfm.tiendaonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service // Marca esta clase como un servicio de Spring para inyección de dependencias
public class ImageUploadService {

    // URL base de la API de FastAPI a la que se conectará el servicio
    private static final String FASTAPI_URL = "http://api-ubuntu-container:5000";
    
    private final WebClient.Builder webClientBuilder; // Builder para crear instancias de WebClient (reactivo)
    
    @Autowired
    private RestTemplate restTemplate; // Cliente HTTP tradicional de Spring

    // Constructor que recibe el WebClient.Builder inyectado por Spring
    public ImageUploadService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    // -------------------------------
    // Método para obtener la lista de imágenes desde la API de FastAPI
    // -------------------------------
    public Mono<String> getImages() {
        String endpoint = "/images/";  // Endpoint de FastAPI para listar imágenes

        return webClientBuilder.build() // Construye un WebClient
                .get() // Realiza una petición GET
                .uri(FASTAPI_URL + endpoint) // Construye la URL completa
                .retrieve() // Ejecuta la solicitud y espera respuesta
                .bodyToMono(String.class) // Convierte la respuesta en un Mono<String>
                .onErrorResume(e -> Mono.just("No se pudieron obtener las imágenes: " + e.getMessage()));
                // Si hay error, retorna un Mono con un mensaje de error
    }
    
    // -------------------------------
    // Método para descargar una imagen como arreglo de bytes
    // -------------------------------
    public Mono<byte[]> downloadImage(String filename) {
        String endpoint = "/download/image/" + filename; // Endpoint de FastAPI para descargar la imagen

        return webClientBuilder.build()
                .get()
                .uri(FASTAPI_URL + endpoint)
                .retrieve()
                .bodyToMono(byte[].class)  // Convierte la respuesta en arreglo de bytes
                .onErrorResume(e -> Mono.empty());  // Retorna un Mono vacío si ocurre un error
    }
    
    // -------------------------------
    // Método para obtener un video desde la API usando RestTemplate (sin reactividad)
    // -------------------------------
    public ResponseEntity<byte[]> getVideoFromExternalApi(String filename) {
        String videoUrl = FASTAPI_URL + "/download/video/" + filename; // Endpoint de FastAPI para descargar video

        // Hacer la solicitud HTTP GET y retornar el ResponseEntity con el contenido en bytes
        return restTemplate.getForEntity(videoUrl, byte[].class);
    }
}
