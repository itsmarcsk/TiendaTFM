package com.tfm.tiendaonline.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tfm.tiendaonline.dao.CarritoDAO;
import com.tfm.tiendaonline.model.Carrito;

@Service
public class CarritoService {

    private final CarritoDAO carritoDAO;

    public CarritoService(CarritoDAO carritoDAO) {
        this.carritoDAO = carritoDAO;
    }

    public void agregarProducto(Carrito carrito) throws SQLException {
        carritoDAO.insertar(carrito);
    }

    public List<Carrito> obtenerCarritoDeUsuario(String email) throws SQLException {
        return carritoDAO.obtenerPorUsuario(email);
    }

    public void actualizarCantidad(Carrito carrito) throws SQLException {
        carritoDAO.actualizar(carrito);
    }

    public void eliminarProducto(int id) throws SQLException {
        carritoDAO.eliminar(id);
    }

    public void vaciarCarrito(String email) throws SQLException {
        carritoDAO.eliminarPorUsuario(email);
    }

    public void vaciarCarritoPorProducto(String email, String productoNombre) throws SQLException {
        carritoDAO.eliminarPorUsuarioPorProducto(email, productoNombre);
    }
}
