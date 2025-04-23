package com.example.ms_kajita.service;

import com.example.ms_kajita.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    public List<Producto> listar();

    public Producto guardar(Producto producto);

    public Producto actualizar(Producto producto);

    public Optional<Producto> listarPorId(Integer id);

    public void eliminarPorId(Integer id);

    public Optional<Producto> obtenerProductoConNombreCategoria(Integer id);


    //opcional
    void decrementarStock(Integer productoId, Integer cantidad); // Nuevo método
}