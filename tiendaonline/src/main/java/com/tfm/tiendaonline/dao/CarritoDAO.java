package com.tfm.tiendaonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;


import javax.sql.DataSource;

import com.tfm.tiendaonline.model.Carrito;

public class CarritoDAO {
    private final DataSource dataSource;

    public CarritoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

     public void insertar(Carrito carrito) throws SQLException {
        String sql = "INSERT INTO carrito (usuario_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, carrito.getUsuarioId());
            ps.setInt(2, carrito.getProductoId());
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
    public List<Carrito> obtenerPorUsuario(int usuarioId) throws SQLException {
        String sql = "SELECT id, usuario_id, producto_id, cantidad FROM carrito WHERE usuario_id = ?";
        List<Carrito> carritoList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Carrito carrito = new Carrito();
                    carrito.setId(rs.getInt("id"));
                    carrito.setUsuarioId(rs.getInt("usuario_id"));
                    carrito.setProductoId(rs.getInt("producto_id"));
                    carrito.setCantidad(rs.getInt("cantidad"));
                    carritoList.add(carrito);
                }
            }
        }
        return carritoList;
    }

    // Actualizar cantidad de un producto en el carrito
    public void actualizar(Carrito carrito) throws SQLException {
        String sql = "UPDATE carrito SET cantidad = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, carrito.getCantidad());
            ps.setInt(2, carrito.getId());
            ps.executeUpdate();
        }
    }

    // Eliminar un producto del carrito por su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM carrito WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Vaciar el carrito de un usuario
    public void eliminarPorUsuario(int usuarioId) throws SQLException {
        String sql = "DELETE FROM carrito WHERE usuario_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ps.executeUpdate();
        }
    }
}
