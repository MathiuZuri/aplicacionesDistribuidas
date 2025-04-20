package com.example.ms_kajita.dto;

public class CategoriaDto {
    private Integer id;
    private String nombre;

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

    public CategoriaDto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public CategoriaDto() {}

    @Override
    public String toString() {
        return "CategoriaDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
