package com.tfm.tiendaonline.controller;

import com.tfm.tiendaonline.model.Pedido;
import com.tfm.tiendaonline.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // Crear un pedido
    @PostMapping
    public String crearPedido(@RequestBody Pedido pedido) {
        try {
            pedidoService.crearPedido(pedido);
            return "Pedido creado correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al crear pedido: " + e.getMessage();
        }
    }

    // Obtener pedido por ID
    @GetMapping("/{id}")
    public Pedido obtenerPedido(@PathVariable int id) {
        try {
            return pedidoService.obtenerPedidoPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Listar todos los pedidos
    @GetMapping
    public List<Pedido> listarPedidos() {
        try {
            return pedidoService.listarPedidos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Listar pedidos de un usuario específico
    @GetMapping("/usuario/{usuarioId}")
    public List<Pedido> listarPedidosPorUsuario(@PathVariable int usuarioId) {
        try {
            return pedidoService.listarPorUsuario(usuarioId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Actualizar pedido
    @PutMapping("/{id}")
    public String actualizarPedido(@PathVariable int id, @RequestBody Pedido pedido) {
        try {
            pedido.setId(id);
            pedidoService.actualizarPedido(pedido);
            return "Pedido actualizado correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al actualizar pedido: " + e.getMessage();
        }
    }

    // Eliminar pedido
    @DeleteMapping("/{id}")
    public String eliminarPedido(@PathVariable int id) {
        try {
            pedidoService.eliminarPedido(id);
            return "Pedido eliminado correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al eliminar pedido: " + e.getMessage();
        }
    }
}
