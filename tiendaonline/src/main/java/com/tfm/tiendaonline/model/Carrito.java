package com.tfm.tiendaonline.model;

public class Carrito {
    private int id;
    private String email;
    private String productoNombre;
    private int cantidad;

    public Carrito() {}

    public Carrito(int cantidad, String email, String productoNombre, int id) {
        this.cantidad = cantidad;
        this.productoNombre = productoNombre;
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getters y Setters
    
}
