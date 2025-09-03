package com.tfm.tiendaonline.service;

import com.tfm.tiendaonline.dao.PedidoDetalleDAO;
import com.tfm.tiendaonline.model.PedidoDetalle;

import java.sql.SQLException;
import java.util.List;

public class PedidoDetalleService {

    private final PedidoDetalleDAO pedidoDetalleDAO;

    public PedidoDetalleService(PedidoDetalleDAO pedidoDetalleDAO) {
        this.pedidoDetalleDAO = pedidoDetalleDAO;
    }

    public void agregarDetalle(PedidoDetalle detalle) throws SQLException {
        pedidoDetalleDAO.insertar(detalle);
    }

    public PedidoDetalle obtenerDetallePorId(int id) throws SQLException {
        return pedidoDetalleDAO.obtenerPorId(id);
    }

    public List<PedidoDetalle> listarDetalles() throws SQLException {
        return pedidoDetalleDAO.obtenerTodos();
    }

    public void actualizarDetalle(PedidoDetalle detalle) throws SQLException {
        pedidoDetalleDAO.actualizar(detalle);
    }

    public void eliminarDetalle(int id) throws SQLException {
        pedidoDetalleDAO.eliminar(id);
    }
}
