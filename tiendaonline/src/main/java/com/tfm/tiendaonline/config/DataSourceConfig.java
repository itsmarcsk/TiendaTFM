package com.tfm.tiendaonline.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tfm.tiendaonline.dao.CarritoDAO;
import com.tfm.tiendaonline.dao.PedidoDAO;
import com.tfm.tiendaonline.dao.PedidoDetalleDAO;
import com.tfm.tiendaonline.dao.ProductoDAO;
import com.tfm.tiendaonline.dao.UsuarioDAO;

/*import com.tfm.clinicaf.dao.CitaDao;
import com.tfm.clinicaf.dao.FisioterapeutaDao;
import com.tfm.clinicaf.dao.PacienteDao;
import com.tfm.clinicaf.dao.RevisionDao;
import com.tfm.clinicaf.dao.TratamientoDao;*/


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

    @Bean
    public CarritoDAO carritoDAO(DataSource dataSource) {
        return new CarritoDAO(dataSource);
    }
    @Bean
    public PedidoDAO pedidoDAO(DataSource dataSource) {
        return new PedidoDAO(dataSource);
    }
    @Bean
    public PedidoDetalleDAO pedidoDetalleDAO(DataSource dataSource) {
        return new PedidoDetalleDAO(dataSource);
    }
}

