package com.tfm.tiendaonline.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Crear usuario
    @PostMapping
    public boolean crearUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.registrarUsuario(usuario);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable int id) {
        try {
            return usuarioService.obtenerUsuarioPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Obtener usuario por email
    @GetMapping("/email/{email}")
    public Usuario obtenerUsuarioPorEmail(@PathVariable String email) {
        try {
            return usuarioService.obtenerUsuarioPorEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/login/{email}/{contrasena}")
    public Map<String, Object> login(@PathVariable String email,
                                     @PathVariable String contrasena) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        boolean ok = usuarioService.login(email, contrasena);

        response.put("success", ok);
        return response;
    }

    // Listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        try {
            return usuarioService.listarUsuarios();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public String actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        try {
            usuario.setId(id);
            usuarioService.actualizarUsuario(usuario);
            return "Usuario actualizado correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al actualizar usuario: " + e.getMessage();
        }
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        try {
            usuarioService.eliminarUsuario(id);
            return "Usuario eliminado correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al eliminar usuario: " + e.getMessage();
        }
    }
}
