package com.tfm.tiendaonline.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tfm.tiendaonline.dao.ProductoDAO;
import com.tfm.tiendaonline.model.Producto;

@Service // Marca esta clase como un servicio de Spring para inyección de dependencias
public class ProductoService {

    private final ProductoDAO productoDAO; // DAO para acceder a la base de datos

    // -------------------------------
    // Constructor para inyectar el DAO
    // -------------------------------
    public ProductoService(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    // -------------------------------
    // Agrega un nuevo producto a la base de datos
    // -------------------------------
    public void agregarProducto(Producto producto) throws SQLException {
        productoDAO.insertar(producto); // Llama al DAO para insertar el producto
    }

    // -------------------------------
    // Lista todos los productos
    // -------------------------------
    public List<Producto> listarProductos() throws SQLException {
        return productoDAO.obtenerTodos(); // Devuelve la lista de productos desde el DAO
    }

    // -------------------------------
    // Elimina un producto por su ID
    // -------------------------------
    public void eliminarProducto(int id) throws SQLException {
        productoDAO.eliminar(id); // Llama al DAO para eliminar el producto por su ID
    }
}
