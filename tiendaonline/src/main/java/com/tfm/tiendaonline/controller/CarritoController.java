package com.tfm.tiendaonline.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tiendaonline.model.Carrito;
import com.tfm.tiendaonline.service.CarritoService;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    // Agregar un producto al carrito
    @PostMapping
    public Carrito agregarProducto(@RequestBody Carrito carrito) {
        try {
            carritoService.agregarProducto(carrito);
            return carrito;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al agregar producto al carrito");
        }
    }

    // Obtener el carrito de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public List<Carrito> obtenerCarrito(@PathVariable int usuarioId) {
        try {
            return carritoService.obtenerCarritoDeUsuario(usuarioId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el carrito del usuario");
        }
    }

    // Actualizar la cantidad de un producto en el carrito
    @PutMapping("/{id}")
    public Carrito actualizarCantidad(@PathVariable int id, @RequestBody Carrito carrito) {
        try {
            carrito.setId(id);
            carritoService.actualizarCantidad(carrito);
            return carrito;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar cantidad del carrito");
        }
    }

    // Eliminar un producto del carrito
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable int id) {
        try {
            carritoService.eliminarProducto(id);
            return "Producto eliminado del carrito correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar producto del carrito");
        }
    }

    // Vaciar el carrito de un usuario
    @DeleteMapping("/usuario/{usuarioId}")
    public String vaciarCarrito(@PathVariable int usuarioId) {
        try {
            carritoService.vaciarCarrito(usuarioId);
            return "Carrito vaciado correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al vaciar el carrito del usuario");
        }
    }
}
