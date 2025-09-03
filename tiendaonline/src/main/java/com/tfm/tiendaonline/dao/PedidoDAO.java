package com.tfm.tiendaonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.tfm.tiendaonline.model.Pedido;

public class PedidoDAO {
    private final DataSource dataSource;

    public PedidoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertar(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedido (usuario_id, total, estado) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pedido.getUsuarioId());
            ps.setDouble(2, pedido.getTotal());
            ps.setString(3, pedido.getEstado());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Pedido> listarPorUsuario(int usuarioId) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT id, usuario_id, fecha, total, estado FROM pedido WHERE usuario_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(new Pedido(
                            rs.getInt("id"),
                            rs.getInt("usuario_id"),
                            rs.getTimestamp("fecha").toLocalDateTime(),
                            rs.getDouble("total"),
                            rs.getString("estado")
                    ));
                }
            }
        }
        return pedidos;
    }

     public Pedido obtenerPorId(int id) throws SQLException {
        String sql = "SELECT id, usuario_id, fecha, total, estado FROM pedido WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setId(rs.getInt("id"));
                    pedido.setUsuarioId(rs.getInt("usuario_id"));
                    pedido.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                    pedido.setTotal(rs.getDouble("total"));
                    pedido.setEstado(rs.getString("estado"));
                    return pedido;
                }
            }
        }
        return null;
    }

    // Obtener todos los pedidos
    public List<Pedido> obtenerTodos() throws SQLException {
        String sql = "SELECT id, usuario_id, fecha, total, estado FROM pedido";
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setUsuarioId(rs.getInt("usuario_id"));
                pedido.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                pedido.setTotal(rs.getDouble("total"));
                pedido.setEstado(rs.getString("estado"));
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    // Actualizar un pedido
    public void actualizar(Pedido pedido) throws SQLException {
        String sql = "UPDATE pedido SET usuario_id = ?, total = ?, estado = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pedido.getUsuarioId());
            ps.setDouble(2, pedido.getTotal());
            ps.setString(3, pedido.getEstado());
            ps.setInt(4, pedido.getId());

            ps.executeUpdate();
        }
    }

    // Eliminar un pedido por ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM pedido WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    
}
