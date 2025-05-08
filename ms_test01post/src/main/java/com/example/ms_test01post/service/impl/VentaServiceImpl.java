package com.example.ms_test01post.service.impl;

import com.example.ms_test01post.dto.Cliente;
import com.example.ms_test01post.dto.ClienteServiceClient;
import com.example.ms_test01post.dto.ProductoDto;
import com.example.ms_test01post.feing.ProductoFeign;
import com.example.ms_test01post.entity.Venta;
import com.example.ms_test01post.repository.VentaRepository;
import com.example.ms_test01post.service.VentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClienteServiceClient clienteServiceClient;

    @Override
    public List<Venta> listar() {
        return ventaRepository.findAll().stream()
                .map(venta -> {
                    String nombreProducto = obtenerNombreProducto(venta.getProductoId());
                    venta.setNombreProducto(nombreProducto);
                    obtenerYSetNombreCliente(venta);
                    return venta;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Venta guardar(Venta venta) {
        // Corregido: Pasa venta.getProductoId() (el Integer)
        ResponseEntity<ProductoDto> productoResponse = productoFeign.obtenerProducto(venta.getProductoId());

        if (productoResponse.getStatusCode().is2xxSuccessful() && productoResponse.getBody() != null) {
            ProductoDto productoDto = productoResponse.getBody();
            if (productoDto.getStock() >= venta.getCantidad()) {
                productoFeign.decrementarStock(venta.getProductoId(), venta.getCantidad());

                venta.setFechaVenta(LocalDateTime.now());
                Venta ventaGuardada = ventaRepository.save(venta);
                ventaGuardada.setNombreProducto(productoDto.getNombre());
                obtenerYSetNombreCliente(ventaGuardada);
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
            // Corregido: Pasa venta.getProductoId() (el Integer)
            ResponseEntity<ProductoDto> productoResponse = productoFeign.obtenerProducto(venta.getProductoId());
            if (productoResponse.getStatusCode().is2xxSuccessful() && productoResponse.getBody() != null) {
                venta.setNombreProducto(productoResponse.getBody().getNombre());
                obtenerYSetNombreCliente(venta);
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
            obtenerYSetNombreCliente(venta);
            return Optional.of(venta);
        }
        return Optional.empty();
    }

    @Override
    public void eliminarPorId(Integer id) {
        ventaRepository.deleteById(id);
    }

    private String obtenerNombreProducto(Integer productoId) {
        // Corregido: Pasa productoId (el Integer)
        ResponseEntity<ProductoDto> productoResponse = productoFeign.obtenerProducto(productoId);
        if (productoResponse.getStatusCode().is2xxSuccessful() && productoResponse.getBody() != null) {
            return productoResponse.getBody().getNombre();
        }
        return "Producto no encontrado";
    }

    private void obtenerYSetNombreCliente(Venta venta) {
        Integer clienteId = venta.getClienteId();
        if (clienteId != null) {
            Cliente clienteInfo = clienteServiceClient.obtenerClienteInfo(clienteId);
            if (clienteInfo != null) {
                venta.setNombreCliente(clienteInfo.getNombreCompleto());
            } else {
                venta.setNombreCliente("Cliente no encontrado");
            }
        } else {
            venta.setNombreCliente("Sin cliente asignado");
        }
    }

}