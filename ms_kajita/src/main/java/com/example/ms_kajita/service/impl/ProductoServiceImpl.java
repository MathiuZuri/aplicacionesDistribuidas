package com.example.ms_kajita.service.impl;

import com.example.ms_kajita.feing.CatalogoFeign;
import com.example.ms_kajita.dto.CategoriaDto;
import com.example.ms_kajita.entity.Producto;
import com.example.ms_kajita.repository.ProductoRepository;
import com.example.ms_kajita.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CatalogoFeign catalogoFeign; // Correcto: inyectaste catalogoFeign

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll().stream()
                .map(producto -> {
                    String categoriaNombre = obtenerNombreCategoria(producto.getCategoriaId());
                    producto.setCategoriaNombre(categoriaNombre);
                    return producto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Producto guardar(Producto producto) {
        Producto productoGuardado = productoRepository.save(producto);
        String categoriaNombre = obtenerNombreCategoria(productoGuardado.getCategoriaId());
        productoGuardado.setCategoriaNombre(categoriaNombre);
        return productoGuardado;
    }

    @Override
    public Producto actualizar(Producto producto) {
        Producto productoActualizado = productoRepository.save(producto);
        String categoriaNombre = obtenerNombreCategoria(productoActualizado.getCategoriaId());
        productoActualizado.setCategoriaNombre(categoriaNombre);
        return productoActualizado;
    }

    @Override
    public Optional<Producto> listarPorId(Integer id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            String categoriaNombre = obtenerNombreCategoria(producto.getCategoriaId());
            producto.setCategoriaNombre(categoriaNombre);
            return Optional.of(producto);
        }
        return Optional.empty();
    }

    @Override
    public void eliminarPorId(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Optional<Producto> obtenerProductoConNombreCategoria(Integer id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            String categoriaNombre = obtenerNombreCategoria(producto.getCategoriaId());
            producto.setCategoriaNombre(categoriaNombre);
            return Optional.of(producto);
        }
        return Optional.empty();
    }

    private String obtenerNombreCategoria(Integer categoriaId) {
        try {
            CategoriaDto categoriaDto = catalogoFeign.buscarCategoria(categoriaId).getBody(); // Correcto: usando catalogoFeign y obteniendo el body
            if (categoriaDto != null) {
                return categoriaDto.getNombre();
            }
        } catch (Exception e) {
            return "Categoría no encontrada";
        }
        return null;
    }


    //desde aqui es opcional
    @Override
    @Transactional // Asegura la atomicidad de la operación
    public void decrementarStock(Integer productoId, Integer cantidad) {
        Optional<Producto> productoOptional = productoRepository.findById(productoId);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                productoRepository.save(producto);
            } else {
                throw new RuntimeException("No hay suficiente stock para decrementar del producto con ID: " + productoId);
            }
        } else {
            throw new RuntimeException("No se encontró el producto con ID: " + productoId);
        }
    }
}