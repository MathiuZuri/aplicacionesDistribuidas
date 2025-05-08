package com.example.ms_test01post.dto;

import jakarta.persistence.*;

import java.time.LocalDateTime;


public class ProductoDto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private Integer categoriaId;
    @Transient
    private String categoriaNombre;
    private Integer stock; // Nuevo atributo para el stock

    // Getters y setters...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public ProductoDto(Integer id, String nombre, String descripcion, LocalDateTime fechaCreacion, Integer categoriaId, String categoriaNombre, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.stock = stock;
    }

    public ProductoDto() {}

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", categoriaId=" + categoriaId +
                ", categoriaNombre='" + categoriaNombre + '\'' +
                ", stock=" + stock +
                '}';
    }
}