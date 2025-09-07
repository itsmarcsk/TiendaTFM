package com.tfm.tiendaonline.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tfm.tiendaonline.dao.UsuarioDAO;
import com.tfm.tiendaonline.model.Usuario;

@Service
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void registrarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.insertar(usuario);
    }

    public Usuario obtenerUsuarioPorId(int id) throws SQLException {
        return usuarioDAO.obtenerPorId(id);
    }

    public Usuario obtenerUsuarioPorEmail(String email) throws SQLException {
        return usuarioDAO.obtenerPorEmail(email);
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioDAO.obtenerTodos();
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.actualizar(usuario);
    }

    public void eliminarUsuario(int id) throws SQLException {
        usuarioDAO.eliminar(id);
    }

    public boolean login(String email, String contrasena) throws SQLException {
        return usuarioDAO.login(email, contrasena);
    }

}
