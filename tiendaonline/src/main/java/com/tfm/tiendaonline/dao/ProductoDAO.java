package com.tfm.tiendaonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.tfm.tiendaonline.model.Producto;

public class ProductoDAO {

    private final DataSource dataSource;

    public ProductoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Insertar un producto
    public void insertar(Producto producto) throws SQLException {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, imagen) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getImagen());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    producto.setId(rs.getInt(1));
                }
            }
        }
    }

    // Obtener producto por ID
    public Producto obtenerPorId(int id) throws SQLException {
        String sql = "SELECT id, nombre, descripcion, precio, stock, imagen, fecha_creacion FROM producto WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearProducto(rs);
                }
            }
        }
        return null;
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodos() throws SQLException {
        String sql = "SELECT id, nombre, descripcion, precio, stock, imagen, fecha_creacion FROM producto";
        List<Producto> productos = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        }
        return productos;
    }

    // Actualizar un producto
    public void actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, stock = ?, imagen = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getImagen());
            ps.setInt(6, producto.getId());
            ps.executeUpdate();
        }
    }

    // Eliminar un producto
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM producto WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Método privado para mapear ResultSet a Producto
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setStock(rs.getInt("stock"));
        producto.setImagen(rs.getString("imagen"));
        producto.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
        return producto;
    }
}
