package com.tfm.tiendaonline.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tiendaonline.model.Producto;
import com.tfm.tiendaonline.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Crear un producto
    @PostMapping
    public String crearProducto(@RequestBody Producto producto) {
        try {
            productoService.agregarProducto(producto);
            return "Producto creado correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al crear producto: " + e.getMessage();
        }
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable int id) {
        try {
            return productoService.obtenerProductoPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Listar todos los productos
    @GetMapping
    public List<Producto> listarProductos() {
        try {
            return productoService.listarProductos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public String actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        try {
            producto.setId(id);
            productoService.actualizarProducto(producto);
            return "Producto actualizado correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al actualizar producto: " + e.getMessage();
        }
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable int id) {
        try {
            productoService.eliminarProducto(id);
            return "Producto eliminado correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al eliminar producto: " + e.getMessage();
        }
    }
}
