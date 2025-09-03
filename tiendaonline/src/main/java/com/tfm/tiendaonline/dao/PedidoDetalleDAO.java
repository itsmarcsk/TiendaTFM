package com.tfm.tiendaonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.tfm.tiendaonline.model.PedidoDetalle;

public class PedidoDetalleDAO {
    private final DataSource dataSource;

    public PedidoDetalleDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertar(PedidoDetalle detalle) throws SQLException {
        String sql = "INSERT INTO pedido_detalle (pedido_id, producto_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detalle.getPedidoId());
            ps.setInt(2, detalle.getProductoId());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioUnitario());
            ps.executeUpdate();
        }
    }

    public List<PedidoDetalle> listarPorPedido(int pedidoId) throws SQLException {
        List<PedidoDetalle> detalles = new ArrayList<>();
        String sql = "SELECT id, pedido_id, producto_id, cantidad, precio_unitario FROM pedido_detalle WHERE pedido_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pedidoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    detalles.add(new PedidoDetalle(
                            rs.getInt("id"),
                            rs.getInt("pedido_id"),
                            rs.getInt("producto_id"),
                            rs.getInt("cantidad"),
                            rs.getDouble("precio_unitario")
                    ));
                }
            }
        }
        return detalles;
    }

    // Obtener un detalle de pedido por ID
    public PedidoDetalle obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pedido_detalle WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearDetalle(rs);
                }
            }
        }
        return null;
    }

    // Obtener todos los detalles de pedidos
    public List<PedidoDetalle> obtenerTodos() throws SQLException {
        List<PedidoDetalle> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido_detalle";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearDetalle(rs));
            }
        }
        return lista;
    }

    // Actualizar un detalle de pedido
    public void actualizar(PedidoDetalle detalle) throws SQLException {
        String sql = "UPDATE pedido_detalle SET pedido_id = ?, producto_id = ?, cantidad = ?, precio_unitario = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detalle.getPedidoId());
            ps.setInt(2, detalle.getProductoId());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioUnitario());
            ps.setInt(5, detalle.getId());
            ps.executeUpdate();
        }
    }

    // Eliminar un detalle de pedido por ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM pedido_detalle WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Mapear ResultSet a objeto PedidoDetalle
    private PedidoDetalle mapearDetalle(ResultSet rs) throws SQLException {
        PedidoDetalle detalle = new PedidoDetalle();
        detalle.setId(rs.getInt("id"));
        detalle.setPedidoId(rs.getInt("pedido_id"));
        detalle.setProductoId(rs.getInt("producto_id"));
        detalle.setCantidad(rs.getInt("cantidad"));
        detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
        return detalle;
    }
}
