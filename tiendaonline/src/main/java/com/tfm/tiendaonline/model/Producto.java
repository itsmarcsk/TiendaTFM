package com.tfm.tiendaonline.model;

import java.sql.Timestamp;

public class Producto {
    // -------------------------------
    // Propiedades del producto
    // -------------------------------
    private int id; // ID único del producto en la base de datos
    private String nombre; // Nombre del producto
    private String descripcion; // Descripción del producto
    private double precio; // Precio del producto
    private int stock; // Cantidad disponible en inventario
    private String imagen; // URL o ruta de la imagen del producto
    private Timestamp fechaCreacion; // Fecha y hora en que se creó el producto

    // -------------------------------
    // Constructor vacío (requerido para frameworks y para crear objetos sin inicializar)
    // -------------------------------
    public Producto() {}

    // -------------------------------
    // Constructor completo
    // -------------------------------
    public Producto(int id, String nombre, String descripcion, double precio, int stock,
                    String imagen, Timestamp fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.fechaCreacion = fechaCreacion;
    }

    // -------------------------------
    // Getters
    // -------------------------------
    public int getId() {
        return id; // Devuelve el ID del producto
    }

    public String getNombre() {
        return nombre; // Devuelve el nombre
    }

    public String getDescripcion() {
        return descripcion; // Devuelve la descripción
    }

    public double getPrecio() {
        return precio; // Devuelve el precio
    }

    public int getStock() {
        return stock; // Devuelve el stock disponible
    }

    public String getImagen() {
        return imagen; // Devuelve la ruta o URL de la imagen
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion; // Devuelve la fecha de creación
    }

    // -------------------------------
    // Setters
    // -------------------------------
    public void setId(int id) {
        this.id = id; // Asigna un nuevo ID
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Asigna un nuevo nombre
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; // Asigna una nueva descripción
    }

    public void setPrecio(double precio) {
        this.precio = precio; // Asigna un nuevo precio
    }

    public void setStock(int stock) {
        this.stock = stock; // Asigna un nuevo stock
    }

    public void setImagen(String imagen) {
        this.imagen = imagen; // Asigna una nueva imagen
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion; // Asigna una nueva fecha de creación
    }

    // -------------------------------
    // Fin de la clase Producto
    // -------------------------------
}
