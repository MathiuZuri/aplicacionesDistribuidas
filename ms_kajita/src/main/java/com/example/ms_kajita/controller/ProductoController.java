package com.example.ms_kajita.controller;

import com.example.ms_kajita.entity.Producto;
import com.example.ms_kajita.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return new ResponseEntity<>(productoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Integer id) {
        return productoService.obtenerProductoConNombreCategoria(id)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto productoCreado = productoService.guardar(producto);
        return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        if (productoService.listarPorId(id).isPresent()) {
            producto.setId(id);
            Producto productoActualizado = productoService.actualizar(producto);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        if (productoService.listarPorId(id).isPresent()) {
            productoService.eliminarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    //opcional
    @PutMapping("/{id}/decrementar-stock")
    public ResponseEntity<?> decrementarStock(@PathVariable Integer id, @RequestParam Integer cantidad) {
        Optional<Producto> productoOptional = productoService.listarPorId(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            if (producto.getStock() >= cantidad) {
                productoService.decrementarStock(id, cantidad); // Asume que tienes este método en tu ProductoService
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("No hay suficiente stock.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}