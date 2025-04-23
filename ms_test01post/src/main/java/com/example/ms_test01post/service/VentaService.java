package com.example.ms_test01post.service;

import com.example.ms_test01post.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    List<Venta> listar();

    Venta guardar(Venta venta);

    Venta actualizar(Venta venta);

    Optional<Venta> listarPorId(Integer id);

    void eliminarPorId(Integer id);
}