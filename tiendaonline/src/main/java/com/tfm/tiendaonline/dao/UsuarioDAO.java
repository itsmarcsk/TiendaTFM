package com.tfm.tiendaonline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.tfm.tiendaonline.model.Usuario;

public class UsuarioDAO {

    private final DataSource dataSource;

    // Constantes para nombres de columna
    private static final String COL_ID = "id";
    private static final String COL_NOMBRE = "nombre";
    private static final String COL_APELLIDOS = "apellidos";
    private static final String COL_EMAIL = "email";
    private static final String COL_CONTRASENA = "contrasena";
    private static final String COL_DIRECCION = "direccion";
    private static final String COL_TELEFONO = "telefono";
    private static final String COL_FECHA_REGISTRO = "fecha_registro";

    // Constantes para queries
    private static final String SQL_SELECT = "SELECT ";

    public UsuarioDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (" + COL_NOMBRE + ", " + COL_APELLIDOS + ", " + COL_EMAIL + ", "
                + COL_CONTRASENA + ", " + COL_DIRECCION + ", " + COL_TELEFONO + ") VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, usuario.getDireccion());
            stmt.setString(6, usuario.getTelefono());
            stmt.executeUpdate();
        }
    }

    public boolean login(String email, String contrasena) throws SQLException {
        String sql = SQL_SELECT + "1 FROM usuario WHERE " + COL_EMAIL + " = ? AND " + COL_CONTRASENA + " = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public Usuario obtenerPorEmail(String email) throws SQLException {
        String sql = SQL_SELECT + COL_ID + ", " + COL_NOMBRE + ", " + COL_APELLIDOS + ", " + COL_EMAIL + ", "
                + COL_CONTRASENA + ", " + COL_DIRECCION + ", " + COL_TELEFONO + ", " + COL_FECHA_REGISTRO
                + " FROM usuario WHERE " + COL_EMAIL + " = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario.Builder()
                        .id(rs.getInt(COL_ID))
                        .nombre(rs.getString(COL_NOMBRE))
                        .apellidos(rs.getString(COL_APELLIDOS))
                        .email(rs.getString(COL_EMAIL))
                        .contrasena(rs.getString(COL_CONTRASENA))
                        .direccion(rs.getString(COL_DIRECCION))
                        .telefono(rs.getString(COL_TELEFONO))
                        .fechaRegistro(rs.getTimestamp(COL_FECHA_REGISTRO))
                        .build();
            }
        }
        return null;
    }

    public List<Usuario> obtenerTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = SQL_SELECT + COL_ID + ", " + COL_NOMBRE + ", " + COL_APELLIDOS + ", " + COL_EMAIL + ", "
                + COL_CONTRASENA + ", " + COL_DIRECCION + ", " + COL_TELEFONO + ", " + COL_FECHA_REGISTRO
                + " FROM usuario";
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Usuario.Builder()
                        .id(rs.getInt(COL_ID))
                        .nombre(rs.getString(COL_NOMBRE))
                        .apellidos(rs.getString(COL_APELLIDOS))
                        .email(rs.getString(COL_EMAIL))
                        .contrasena(rs.getString(COL_CONTRASENA))
                        .direccion(rs.getString(COL_DIRECCION))
                        .telefono(rs.getString(COL_TELEFONO))
                        .fechaRegistro(rs.getTimestamp(COL_FECHA_REGISTRO))
                        .build());
            }
        }
        return lista;
    }

    public void eliminar(String email) throws SQLException {
        String sql = "DELETE FROM usuario WHERE " + COL_EMAIL + " = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        }
    }

    public void actualizarContrasena(String email, String nuevaContrasena) throws SQLException {
        String sql = "UPDATE usuario SET " + COL_CONTRASENA + " = ? WHERE " + COL_EMAIL + " = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevaContrasena);
            ps.setString(2, email);
            ps.executeUpdate();
        }
    }

}
