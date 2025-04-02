package com.example.ms_kajita.service.impl;

import com.example.ms_kajita.entity.Categoria;
import com.example.ms_kajita.entity.Producto;
import com.example.ms_kajita.repository.ProductoRepository;
import com.example.ms_kajita.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate; // Import RestTemplate

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RestTemplate restTemplate; // Inyecta RestTemplate

    private final String categoriaServiceUrl = "http://ms-catalogo/categoria/"; // URL del microservicio de categorías

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> listarPorId(Integer id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            String categoriaNombre = getCategoriaNombre(producto.getCategoriaId());
            producto.setCategoriaNombre(categoriaNombre);
            return Optional.of(producto);
        }
        return productoOptional;
    }

    @Override
    public void eliminarPorId(Integer id) {
        productoRepository.deleteById(id);
    }

    private String getCategoriaNombre(Integer categoriaId) {
        try {
            Categoria categoria = restTemplate.getForObject(categoriaServiceUrl + categoriaId, Categoria.class);
            if (categoria != null) {
                return categoria.getNombre();
            }
        } catch (Exception e) {
            // Manejar la excepción (por ejemplo, loggear el error)
            return "Categoria no encontrada";
        }
        return null;
    }
}