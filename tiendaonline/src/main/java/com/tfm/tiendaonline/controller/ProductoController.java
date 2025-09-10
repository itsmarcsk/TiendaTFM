package com.tfm.tiendaonline.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tiendaonline.model.Producto;
import com.tfm.tiendaonline.service.ProductoService;

@RestController // Marca esta clase como un controlador REST en Spring
@RequestMapping("/productos") // Prefijo para todas las rutas de este controlador
public class ProductoController {

    private final ProductoService productoService; // Servicio que encapsula la lógica de negocio de productos

    // Constructor que inyecta el servicio
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // -------------------------------
    // Crear un producto
    // -------------------------------
    @PostMapping
    public String crearProducto(@RequestBody Producto producto) {
        try {
            productoService.agregarProducto(producto); // Llama al servicio para insertar el producto
            return "Producto creado correctamente"; // Mensaje de éxito
        } catch (SQLException e) {
            return "Error al crear producto: " + e.getMessage(); // Manejo de errores
        }
    }

    // -------------------------------
    // Listar todos los productos
    // -------------------------------
    @GetMapping
    public List<Producto> listarProductos() {
        try {
            return productoService.listarProductos(); // Llama al servicio para obtener todos los productos
        } catch (SQLException e) {
            return new ArrayList<>(); // Devuelve lista vacía en caso de error para evitar null
        }
    }

    // -------------------------------
    // Eliminar producto por ID
    // -------------------------------
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable int id) {
        try {
            productoService.eliminarProducto(id); // Llama al servicio para eliminar el producto
            return "Producto eliminado correctamente"; // Mensaje de éxito
        } catch (SQLException e) {
            return "Error al eliminar producto: " + e.getMessage(); // Manejo de errores
        }
    }
}
