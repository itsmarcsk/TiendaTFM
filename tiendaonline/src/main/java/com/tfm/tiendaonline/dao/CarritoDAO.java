package com.tfm.tiendaonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.tfm.tiendaonline.model.Carrito;

public class CarritoDAO {
    private final DataSource dataSource;

    public CarritoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

     public void insertar(Carrito carrito) throws SQLException {
        String sql = "INSERT INTO carrito (usuario_email, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, carrito.getEmail());
            ps.setString(2, carrito.getProductoNombre());
            ps.setInt(3, carrito.getCantidad());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    carrito.setId(rs.getInt(1));
                }
            }
        }
    }

    // Obtener todos los productos de un usuario
    public List<Carrito> obtenerPorUsuario(String email) throws SQLException {
        String sql = "SELECT id, usuario_email, producto_id, cantidad FROM carrito WHERE usuario_email = ?";
        List<Carrito> carritoList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Carrito carrito = new Carrito();
                    carrito.setId(rs.getInt("id"));
                    carrito.setEmail(rs.getString("usuario_email"));
                    carrito.setProductoNombre(rs.getString("producto_nombre"));
                    carrito.setCantidad(rs.getInt("cantidad"));
                    carritoList.add(carrito);
                }
            }
        }
        return carritoList;
    }

    // Actualizar cantidad de un producto en el carrito
    public void actualizar(Carrito carrito) throws SQLException {
        String sql = "UPDATE carrito SET cantidad = ? WHERE usuario_email = ? AND producto_nombre = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, carrito.getCantidad());
            ps.setInt(2, carrito.getId());
            ps.executeUpdate();
        }
    }

    // Eliminar un producto del carrito por su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM carrito WHERE usuario_email = ? AND producto_nombre = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Vaciar el carrito de un usuario
    public void eliminarPorUsuario(String usuarioEmail) throws SQLException {
        String sql = "DELETE FROM carrito WHERE usuario_email = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuarioEmail);
            ps.executeUpdate();
        }
    }

    // Vaciar el carrito de un usuario por producto 
    public void eliminarPorUsuarioPorProducto(String usuarioEmail, String productoNombre) throws SQLException {
        String sql = "DELETE FROM carrito WHERE usuario_email = ? AND producto_nombre = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuarioEmail);
            ps.setString(2, productoNombre);
            ps.executeUpdate();
        }
    }
}
