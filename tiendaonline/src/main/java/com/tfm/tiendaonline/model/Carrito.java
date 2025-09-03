package com.tfm.tiendaonline.model;

public class Carrito {
    private int id;
    private int usuarioId;
    private int productoId;
    private int cantidad;

    public Carrito() {}

    public Carrito(int id, int usuarioId, int productoId, int cantidad) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public int getProductoId() {
        return productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getters y Setters
    
}
