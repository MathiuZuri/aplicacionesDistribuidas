package com.example.ms_test01post.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime fechaVenta;

    @Column(name = "producto_id")
    private Integer productoId;

    @Transient
    private String nombreProducto; // Se llenará al obtener el nombre del producto

    private Integer cantidad;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Venta() {
    }

    public Venta(Integer id, LocalDateTime fechaVenta, Integer productoId, Integer cantidad) {
        this.id = id;
        this.fechaVenta = fechaVenta;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", fechaVenta=" + fechaVenta +
                ", productoId=" + productoId +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}