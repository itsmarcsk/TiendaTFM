package com.tfm.tiendaonline.service;

import com.tfm.tiendaonline.dao.CarritoDAO;
import com.tfm.tiendaonline.model.Carrito;

import java.sql.SQLException;
import java.util.List;

public class CarritoService {

    private final CarritoDAO carritoDAO;

    public CarritoService(CarritoDAO carritoDAO) {
        this.carritoDAO = carritoDAO;
    }

    public void agregarProducto(Carrito carrito) throws SQLException {
        carritoDAO.insertar(carrito);
    }

    public List<Carrito> obtenerCarritoDeUsuario(int usuarioId) throws SQLException {
        return carritoDAO.obtenerPorUsuario(usuarioId);
    }

    public void actualizarCantidad(Carrito carrito) throws SQLException {
        carritoDAO.actualizar(carrito);
    }

    public void eliminarProducto(int id) throws SQLException {
        carritoDAO.eliminar(id);
    }

    public void vaciarCarrito(int usuarioId) throws SQLException {
        carritoDAO.eliminarPorUsuario(usuarioId);
    }
}
