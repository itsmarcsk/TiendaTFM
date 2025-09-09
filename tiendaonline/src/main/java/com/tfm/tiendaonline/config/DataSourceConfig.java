package com.tfm.tiendaonline.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tfm.tiendaonline.dao.ProductoDAO;
import com.tfm.tiendaonline.dao.UsuarioDAO;


@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws InterruptedException {
        int retries = 10;
        while (retries-- > 0) {
            try {
                DataSource ds = DataSourceBuilder.create()
                    .url("jdbc:mysql://mysql:3306/tiendaonline?serverTimezone=UTC")
                    .username("root")
                    .password("root")
                    .build();
                ds.getConnection().close();
                return ds;
            } catch (Exception ex) {
                System.out.println("⏳ Esperando base de datos...");
                Thread.sleep(5000);
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

