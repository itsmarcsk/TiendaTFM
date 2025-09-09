package com.tfm.tiendaonline.model;

import java.sql.Timestamp;

public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasena;
    private String direccion;
    private String telefono;
    private Timestamp fechaRegistro;

    public Usuario() {}

    public Usuario(int id, String nombre, String apellidos, String email, String contrasena, String direccion, String telefono, Timestamp fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
