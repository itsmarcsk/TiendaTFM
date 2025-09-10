package com.tfm.tiendaonline.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tfm.tiendaonline.dao.ProductoDAO;
import com.tfm.tiendaonline.dao.UsuarioDAO;

@Configuration // Marca esta clase como configuración de Spring
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    // -------------------------------
    // Configuración del DataSource con reintentos de conexión
    // -------------------------------
    @Bean
    public DataSource dataSource() {
        int maxRetries = 10; // Número máximo de intentos de conexión

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                // Construye el DataSource usando las credenciales y URL de MySQL
                DataSource ds = DataSourceBuilder.create()
                        .url("jdbc:mysql://mysql:3306/tiendaonline?serverTimezone=UTC")
                        .username("root")
                        .password("root")
                        .build();
                
                ds.getConnection().close(); // Verifica la conexión abriendo y cerrando
                return ds; // Retorna el DataSource si la conexión fue exitosa
            } catch (SQLException ex) {
                logger.warn("Intento {} de conexión fallido: {}", attempt, ex.getMessage());
                // Si se alcanzó el último intento, lanza una excepción
                if (attempt == maxRetries) {
                    throw new IllegalStateException(
                            "No se pudo conectar a la base de datos después de " + maxRetries + " intentos", ex);
                }
                // Si no es el último intento, continúa reintentando
            }
        }

        // Esta línea no debería alcanzarse
        throw new IllegalStateException("❌ No se pudo conectar a la base de datos");
    }

    // -------------------------------
    // Bean para UsuarioDAO usando el DataSource
    // -------------------------------
    @Bean
    public UsuarioDAO usuarioDao(DataSource dataSource) {
        return new UsuarioDAO(dataSource);
    }

    // -------------------------------
    // Bean para ProductoDAO usando el DataSource
    // -------------------------------
    @Bean
    public ProductoDAO productoDAO(DataSource dataSource) {
        return new ProductoDAO(dataSource);
    }
}
