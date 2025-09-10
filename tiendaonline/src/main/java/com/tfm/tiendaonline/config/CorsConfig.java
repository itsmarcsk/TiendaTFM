package com.tfm.tiendaonline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marca esta clase como una clase de configuración de Spring
public class CorsConfig {

    // -------------------------------
    // Bean para configurar CORS (Cross-Origin Resource Sharing)
    // -------------------------------
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica CORS a todas las rutas de la aplicación
                        .allowedOrigins("http://localhost:4300") // Permite solicitudes solo desde este origen (tu frontend React)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                        .allowCredentials(true); // Permite enviar cookies o headers de autenticación
            }
        };
    }
}
