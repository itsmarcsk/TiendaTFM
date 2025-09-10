package com.tfm.tiendaonline.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tfm.tiendaonline.dao.UsuarioDAO;
import com.tfm.tiendaonline.model.Usuario;

@Service // Marca esta clase como un servicio de Spring para inyección de dependencias
public class UsuarioService {

    private final UsuarioDAO usuarioDAO; // DAO para acceder a la base de datos de usuarios

    // -------------------------------
    // Constructor para inyectar el DAO
    // -------------------------------
    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    // -------------------------------
    // Registra un nuevo usuario
    // -------------------------------
    public void registrarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.insertar(usuario); // Llama al DAO para insertar el usuario
    }

    // -------------------------------
    // Obtiene un usuario por su email
    // -------------------------------
    public Usuario obtenerUsuarioPorEmail(String email) throws SQLException {
        return usuarioDAO.obtenerPorEmail(email); // Llama al DAO para recuperar el usuario
    }

    // -------------------------------
    // Lista todos los usuarios
    // -------------------------------
    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioDAO.obtenerTodos(); // Devuelve la lista completa de usuarios
    }

    // -------------------------------
    // Elimina un usuario por su email
    // -------------------------------
    public void eliminarUsuario(String email) throws SQLException {
        usuarioDAO.eliminar(email); // Llama al DAO para eliminar el usuario
    }

    // -------------------------------
    // Verifica el login de un usuario
    // -------------------------------
    public boolean login(String email, String contrasena) throws SQLException {
        return usuarioDAO.login(email, contrasena); // Devuelve true si las credenciales son correctas
    }

    // -------------------------------
    // Actualiza la contraseña de un usuario
    // -------------------------------
    public void actualizarContrasena(String email, String nuevaContrasena) throws SQLException {
        usuarioDAO.actualizarContrasena(email, nuevaContrasena); // Llama al DAO para actualizar la contraseña
    }
}
