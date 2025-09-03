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

import com.tfm.tiendaonline.model.PedidoDetalle;
import com.tfm.tiendaonline.service.PedidoDetalleService;

@RestController
@RequestMapping("/pedidos-detalle")
public class PedidoDetalleController {

    private final PedidoDetalleService pedidoDetalleService;

    public PedidoDetalleController(PedidoDetalleService pedidoDetalleService) {
        this.pedidoDetalleService = pedidoDetalleService;
    }

    // Crear un nuevo detalle de pedido
    @PostMapping
    public PedidoDetalle crearDetalle(@RequestBody PedidoDetalle detalle) {
        try {
            pedidoDetalleService.agregarDetalle(detalle);
            return detalle;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el detalle de pedido");
        }
    }

    // Obtener un detalle por ID
    @GetMapping("/{id}")
    public PedidoDetalle obtenerDetalle(@PathVariable int id) {
        try {
            return pedidoDetalleService.obtenerDetallePorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el detalle de pedido");
        }
    }

    // Listar todos los detalles
    @GetMapping
    public List<PedidoDetalle> listarDetalles() {
        try {
            return pedidoDetalleService.listarDetalles();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al listar detalles de pedidos");
        }
    }

    // Actualizar un detalle
    @PutMapping("/{id}")
    public PedidoDetalle actualizarDetalle(@PathVariable int id, @RequestBody PedidoDetalle detalle) {
        try {
            detalle.setId(id);
            pedidoDetalleService.actualizarDetalle(detalle);
            return detalle;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el detalle de pedido");
        }
    }

    // Eliminar un detalle
    @DeleteMapping("/{id}")
    public String eliminarDetalle(@PathVariable int id) {
        try {
            pedidoDetalleService.eliminarDetalle(id);
            return "Detalle eliminado correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar el detalle de pedido");
        }
    }
}
