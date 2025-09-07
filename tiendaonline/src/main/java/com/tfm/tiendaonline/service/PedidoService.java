package com.tfm.tiendaonline.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tfm.tiendaonline.dao.PedidoDAO;
import com.tfm.tiendaonline.model.Pedido;

@Service
public class PedidoService {

    private final PedidoDAO pedidoDAO;

    public PedidoService(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public void crearPedido(Pedido pedido) throws SQLException {
        pedidoDAO.insertar(pedido);
    }

    public List<Pedido> listarPorUsuario(int usuarioId) throws SQLException {
    return pedidoDAO.listarPorUsuario(usuarioId);
    }

    
    public Pedido obtenerPedidoPorId(int id) throws SQLException {
        return pedidoDAO.obtenerPorId(id);
    }

    public List<Pedido> listarPedidos() throws SQLException {
        return pedidoDAO.obtenerTodos();
    }

    public void actualizarPedido(Pedido pedido) throws SQLException {
        pedidoDAO.actualizar(pedido);
    }

    public void eliminarPedido(int id) throws SQLException {
        pedidoDAO.eliminar(id);
    }
}
