package com.tfm.tiendaonline.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tiendaonline.model.Usuario;
import com.tfm.tiendaonline.service.UsuarioService;

@RestController // Marca esta clase como un controlador REST en Spring
@RequestMapping("/usuarios") // Prefijo para todas las rutas de este controlador
public class UsuarioController {

    private final UsuarioService usuarioService; // Servicio que encapsula la lógica de negocio de usuarios

    // Constructor que inyecta el servicio
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // -------------------------------
    // Crear usuario
    // -------------------------------
    @PostMapping
    public boolean crearUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.registrarUsuario(usuario); // Llama al servicio para registrar un usuario
            return true; // Retorna true si se creó correctamente
        } catch (SQLException e) {
            return false; // Retorna false en caso de error
        }
    }

    // -------------------------------
    // Obtener usuario por email
    // -------------------------------
    @GetMapping("/email/{email}")
    public Usuario obtenerUsuarioPorEmail(@PathVariable String email) {
        try {
            return usuarioService.obtenerUsuarioPorEmail(email); // Devuelve el usuario
        } catch (SQLException e) {
            return null; // Devuelve null si hay error
        }
    }

    // -------------------------------
    // Verificar login de usuario
    // -------------------------------
    @GetMapping("/login/{email}/{contrasena}")
    public Map<String, Object> login(@PathVariable String email,
                                     @PathVariable String contrasena) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        boolean ok = usuarioService.login(email, contrasena); // Verifica las credenciales

        response.put("success", ok); // Retorna un mapa con la clave "success" y valor true/false
        return response;
    }

    // -------------------------------
    // Listar todos los usuarios
    // -------------------------------
    @GetMapping
    public List<Usuario> listarUsuarios() {
        try {
            return usuarioService.listarUsuarios(); // Devuelve la lista de usuarios
        } catch (SQLException e) {
            return new ArrayList<>(); // Devuelve lista vacía en caso de error
        }
    }

    // -------------------------------
    // Eliminar usuario por email
    // -------------------------------
    @DeleteMapping("/{email}")
    public String eliminarUsuario(@PathVariable String email) {
        try {
            usuarioService.eliminarUsuario(email); // Llama al servicio para eliminar usuario
            return "Usuario eliminado correctamente";
        } catch (SQLException e) {
            return "Error al eliminar usuario: " + e.getMessage();
        }
    }

    // -------------------------------
    // Actualizar contraseña de un usuario
    // -------------------------------
    @PutMapping("/{email}/contrasena")
    public String actualizarContrasena(@PathVariable String email, @RequestBody String contrasena) {
        try {
            usuarioService.actualizarContrasena(email, contrasena); // Llama al servicio para actualizar contraseña
            return "Contraseña actualizada correctamente";
        } catch (SQLException e) {
            return "Error al actualizar contraseña: " + e.getMessage();
        }
    }

}
