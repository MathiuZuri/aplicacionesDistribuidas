package com.example.ms_test01post.dto;

import java.time.LocalDate;

public class Cliente {
    private Integer clienteId;
    private String nombreCompleto;
    private String dni;
    private String direccion;
    private LocalDate fechaNacimiento;

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente(Integer clienteId, String nombreCompleto, String dni, String direccion, LocalDate fechaNacimiento) {
        this.clienteId = clienteId;
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente() {}

    @Override
    public String toString() {
        return "ClienteDto{" +
                "clienteId=" + clienteId +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}