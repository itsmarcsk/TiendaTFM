package com.tfm.tiendaonline.controller;

import com.tfm.tiendaonline.model.Usuario;
import com.tfm.tiendaonline.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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
    public String crearUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.registrarUsuario(usuario);
            return "Usuario creado correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al crear usuario: " + e.getMessage();
        }
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable int id) {
        try {
            return usuarioService.obtenerUsuarioPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
