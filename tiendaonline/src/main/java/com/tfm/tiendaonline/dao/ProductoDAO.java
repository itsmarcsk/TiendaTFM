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

    private final DataSource dataSource; // Fuente de conexiones a la base de datos

    // Constructor que recibe el DataSource
    public ProductoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // -------------------------------
    // Insertar un nuevo producto
    // -------------------------------
    public void insertar(Producto producto) throws SQLException {
        // SQL con parámetros para evitar SQL Injection
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, imagen) VALUES (?, ?, ?, ?, ?)";
        
        // try-with-resources para asegurar el cierre de la conexión y el PreparedStatement
        try (Connection conn = dataSource.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Asignar valores del objeto Producto a los parámetros de la consulta
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getImagen());

            ps.executeUpdate(); // Ejecuta la inserción

            // Obtener el ID generado automáticamente y asignarlo al objeto Producto
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    producto.setId(rs.getInt(1));
                }
            }
        }
    }

    // -------------------------------
    // Obtener todos los productos
    // -------------------------------
    public List<Producto> obtenerTodos() throws SQLException {
        // Consulta que devuelve todos los productos
        String sql = "SELECT id, nombre, descripcion, precio, stock, imagen, fecha_creacion FROM producto";
        List<Producto> productos = new ArrayList<>();

        // Ejecuta la consulta y recorre el ResultSet
        try (Connection conn = dataSource.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearProducto(rs)); // Convierte cada fila en un objeto Producto
            }
        }
        return productos; // Devuelve la lista completa
    }

    // -------------------------------
    // Eliminar un producto por ID
    // -------------------------------
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM producto WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id); // Asigna el ID al parámetro
            ps.executeUpdate(); // Ejecuta la eliminación
        }
    }

    // -------------------------------
    // Método privado para mapear ResultSet a Producto
    // -------------------------------
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("id")); // Obtiene el ID de la fila
        producto.setNombre(rs.getString("nombre")); // Obtiene el nombre
        producto.setDescripcion(rs.getString("descripcion")); // Obtiene la descripción
        producto.setPrecio(rs.getDouble("precio")); // Obtiene el precio
        producto.setStock(rs.getInt("stock")); // Obtiene el stock
        producto.setImagen(rs.getString("imagen")); // Obtiene la URL/ubicación de la imagen
        producto.setFechaCreacion(rs.getTimestamp("fecha_creacion")); // Obtiene la fecha de creación
        return producto; // Devuelve el objeto Producto mapeado
    }
}
