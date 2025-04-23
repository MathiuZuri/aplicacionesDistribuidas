package com.example.ms_test01post.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
public class Venta {

    public Venta(Integer id, Integer productoId, Integer cantidad, LocalDateTime fechaVenta, String nombreProducto, Integer clienteId, String nombreCliente) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.fechaVenta = fechaVenta;
        this.nombreProducto = nombreProducto;
        this.clienteId = clienteId;
        this.nombreCliente = nombreCliente;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "producto_id")
    private Integer productoId;

    @Column(name = "cantidad")
    private Integer cantidad;


    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;

    @Transient // No se persiste en la base de datos, solo para mostrar
    private String nombreProducto;

    @Column(name = "cliente_id") // Campo para el ID del cliente (proveniente del servicio C#)
    private Integer clienteId;

    @Transient // No se persiste en la base de datos, solo para mostrar
    private String nombreCliente;

    public Venta() {

    }

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", productoId=" + productoId +
                ", cantidad=" + cantidad +
                ", fechaVenta=" + fechaVenta +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", clienteId=" + clienteId +
                ", nombreCliente='" + nombreCliente + '\'' +
                '}';
    }
}
