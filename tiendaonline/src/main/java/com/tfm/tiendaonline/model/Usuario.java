package com.tfm.tiendaonline.model;

import java.sql.Timestamp;

public class Usuario {
    // -------------------------------
    // Propiedades del usuario
    // -------------------------------
    private int id; // ID único del usuario en la base de datos
    private String nombre; // Nombre del usuario
    private String apellidos; // Apellidos del usuario
    private String email; // Correo electrónico (identificador único)
    private String contrasena; // Contraseña del usuario (se recomienda almacenar hashed)
    private String direccion; // Dirección del usuario
    private String telefono; // Teléfono del usuario
    private Timestamp fechaRegistro; // Fecha y hora de registro

    // -------------------------------
    // Constructor vacío (requerido para frameworks o creación inicial de objetos)
    // -------------------------------
    public Usuario() {}

    // -------------------------------
    // Constructor privado que recibe un Builder
    // -------------------------------
    private Usuario(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.apellidos = builder.apellidos;
        this.email = builder.email;
        this.contrasena = builder.contrasena;
        this.direccion = builder.direccion;
        this.telefono = builder.telefono;
        this.fechaRegistro = builder.fechaRegistro;
    }

    // -------------------------------
    // Getters
    // -------------------------------
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getEmail() { return email; }
    public String getContrasena() { return contrasena; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }

    // -------------------------------
    // Setters
    // Solo algunos campos se exponen para modificación directa
    // -------------------------------
    public void setId(int id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }

    // -------------------------------
    // Builder estático para facilitar la creación de objetos Usuario
    // -------------------------------
    public static class Builder {
        private int id;
        private String nombre;
        private String apellidos;
        private String email;
        private String contrasena;
        private String direccion;
        private String telefono;
        private Timestamp fechaRegistro;

        // Cada método Builder asigna un campo y devuelve el Builder para encadenar llamadas
        public Builder id(int id) { this.id = id; return this; }
        public Builder nombre(String nombre) { this.nombre = nombre; return this; }
        public Builder apellidos(String apellidos) { this.apellidos = apellidos; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder contrasena(String contrasena) { this.contrasena = contrasena; return this; }
        public Builder direccion(String direccion) { this.direccion = direccion; return this; }
        public Builder telefono(String telefono) { this.telefono = telefono; return this; }
        public Builder fechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; return this; }

        // Método build que crea un objeto Usuario usando el Builder
        public Usuario build() {
            return new Usuario(this);
        }
    }
}
