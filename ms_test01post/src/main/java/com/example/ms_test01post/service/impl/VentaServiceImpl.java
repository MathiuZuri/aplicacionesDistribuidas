package com.example.ms_test01post.service.impl;

import com.example.ms_test01post.dto.Producto; // Asegúrate de tener este DTO
import com.example.ms_test01post.feing.ProductoFeign;
import com.example.ms_test01post.entity.Venta;
import com.example.ms_test01post.repository.VentaRepository;
import com.example.ms_test01post.service.VentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoFeign productoFeign; // Inyecta el Feign Client de Producto

    @Override
    public List<Venta> listar() {
        return ventaRepository.findAll().stream()
                .map(venta -> {
                    String nombreProducto = obtenerNombreProducto(venta.getProductoId());
                    venta.setNombreProducto(nombreProducto);
                    return venta;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Venta guardar(Venta venta) {
        ResponseEntity<Optional<Producto>> productoResponse = productoFeign.obtenerProducto(venta.getProductoId());

        if (productoResponse.getStatusCode().is2xxSuccessful() && productoResponse.getBody().isPresent()) {
            Producto producto = productoResponse.getBody().get();
            if (producto.getStock() >= venta.getCantidad()) {
                // Llamar al microservicio de productos para decrementar el stock
                productoFeign.decrementarStock(venta.getProductoId(), venta.getCantidad());

                venta.setFechaVenta(LocalDateTime.now());
                Venta ventaGuardada = ventaRepository.save(venta);
                ventaGuardada.setNombreProducto(producto.getNombre());
                return ventaGuardada;
            } else {
                throw new RuntimeException("No hay suficiente stock para el producto con ID: " + venta.getProductoId());
            }
        } else {
            throw new RuntimeException("No se encontró el producto con ID: " + venta.getProductoId());
        }
    }

    @Override
    public Venta actualizar(Venta venta) {
        Optional<Venta> ventaOptional = ventaRepository.findById(venta.getId());
        if (ventaOptional.isPresent()) {
            ResponseEntity<Optional<Producto>> productoResponse = productoFeign.obtenerProducto(venta.getProductoId());
            if (productoResponse.getStatusCode().is2xxSuccessful() && productoResponse.getBody().isPresent()) {
                venta.setNombreProducto(productoResponse.getBody().get().getNombre());
                return ventaRepository.save(venta);
            } else {
                throw new RuntimeException("No se encontró el producto con ID: " + venta.getProductoId());
            }
        }
        return null;
    }

    @Override
    public Optional<Venta> listarPorId(Integer id) {
        Optional<Venta> ventaOptional = ventaRepository.findById(id);
        if (ventaOptional.isPresent()) {
            Venta venta = ventaOptional.get();
            String nombreProducto = obtenerNombreProducto(venta.getProductoId());
            venta.setNombreProducto(nombreProducto);
            return Optional.of(venta);
        }
        return Optional.empty();
    }

    @Override
    public void eliminarPorId(Integer id) {
        ventaRepository.deleteById(id);
    }

    private String obtenerNombreProducto(Integer productoId) {
        ResponseEntity<Optional<Producto>> productoResponse = productoFeign.obtenerProducto(productoId);
        if (productoResponse.getStatusCode().is2xxSuccessful() && productoResponse.getBody().isPresent()) {
            return productoResponse.getBody().get().getNombre();
        }
        return "Producto no encontrado";
    }
}
