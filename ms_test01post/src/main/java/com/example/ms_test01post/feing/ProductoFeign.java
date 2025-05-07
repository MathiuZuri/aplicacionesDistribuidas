package com.example.ms_test01post.feing;

import com.example.ms_test01post.dto.Producto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "ms-kajita-service", path = "/productos") // El nombre del servicio de productos (debe coincidir con el nombre registrado en Eureka o la configuración)
public interface ProductoFeign {

    @GetMapping("/{id}") // La ruta del endpoint en el microservicio de productos para obtener un producto por su ID
    @CircuitBreaker(name = "listarVentaPorIdCB", fallbackMethod = "fallbackVentaById")
    ResponseEntity<Optional<Producto>> obtenerProducto(@PathVariable Integer id);

    default ResponseEntity<Producto> fallbackVentaById(Integer id, Exception e) {
        Producto producto = new Producto();
        producto.setNombre("Servicio no disponible de producto");
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}/decrementar-stock") // Define el endpoint para decrementar el stock
    ResponseEntity<Void> decrementarStock(@PathVariable Integer id, @RequestParam Integer cantidad);



}

