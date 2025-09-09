package com.tfm.tiendaonline.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tfm.tiendaonline.dao.ProductoDAO;
import com.tfm.tiendaonline.model.Producto;

@Service
public class ProductoService {

    private final ProductoDAO productoDAO;

    // Constructor para inyectar el DAO
    public ProductoService(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    // Agrega un nuevo producto a la base de datos
    public void agregarProducto(Producto producto) throws SQLException {
        productoDAO.insertar(producto);
    }


    // Lista todos los productos
    public List<Producto> listarProductos() throws SQLException {
        return productoDAO.obtenerTodos();
    }

    // Elimina un producto por su ID
    public void eliminarProducto(int id) throws SQLException {
        productoDAO.eliminar(id);
    }
}
