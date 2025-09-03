package com.tfm.tiendaonline.model;

public class PedidoDetalle {
    private int id;
    private int pedidoId;
    private int productoId;
    private int cantidad;
    private double precioUnitario;

    public PedidoDetalle() {}

    public PedidoDetalle(int id, int pedidoId, int productoId, int cantidad, double precioUnitario) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getId() {
        return id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public int getProductoId() {
        return productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters
    
    
}
