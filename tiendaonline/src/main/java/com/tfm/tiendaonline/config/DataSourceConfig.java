package com.tfm.tiendaonline.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tfm.tiendaonline.dao.ProductoDAO;
import com.tfm.tiendaonline.dao.UsuarioDAO;

@Configuration
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    public DataSource dataSource() {
        int maxRetries = 10;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                DataSource ds = DataSourceBuilder.create()
                        .url("jdbc:mysql://mysql:3306/tiendaonline?serverTimezone=UTC")
                        .username("root")
                        .password("root")
                        .build();
                ds.getConnection().close();
                return ds;
            } catch (SQLException ex) {
                logger.warn("Intento {} de conexión fallido: {}", attempt, ex.getMessage());
                // Puedes re-lanzar con contexto si ya no hay más intentos
                if (attempt == maxRetries) {
                    throw new IllegalStateException(
                            "No se pudo conectar a la base de datos después de " + maxRetries + " intentos", ex);
                }
            }

        }

        throw new IllegalStateException("❌ No se pudo conectar a la base de datos");
    }

    @Bean
    public UsuarioDAO usuarioDao(DataSource dataSource) {
        return new UsuarioDAO(dataSource);
    }

    @Bean
    public ProductoDAO productoDAO(DataSource dataSource) {
        return new ProductoDAO(dataSource);
    }
}
