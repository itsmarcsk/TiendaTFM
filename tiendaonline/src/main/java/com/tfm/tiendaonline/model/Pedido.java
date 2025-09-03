package com.tfm.tiendaonline.model;

import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private int usuarioId;
    private LocalDateTime fecha;
    private double total;
    private String estado; // pendiente, pagado, enviado, entregado, cancelado

    public Pedido() {}

    public Pedido(int id, int usuarioId, LocalDateTime fecha, double total, String estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Getters y Setters
    
    
}
