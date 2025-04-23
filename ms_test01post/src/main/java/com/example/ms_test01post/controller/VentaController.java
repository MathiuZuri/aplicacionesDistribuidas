package com.example.ms_test01post.controller;

import com.example.ms_test01post.entity.Venta;
import com.example.ms_test01post.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping()
    public ResponseEntity<List<Venta>> list() {
        return new ResponseEntity<>(ventaService.listar(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody Venta venta) {
        try {
            Venta ventaGuardada = ventaService.guardar(venta);
            return new ResponseEntity<>(ventaGuardada, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<Venta> update(@RequestBody Venta venta) {
        if (ventaService.listarPorId(venta.getId()).isPresent()) {
            return new ResponseEntity<>(ventaService.actualizar(venta), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> listById(@PathVariable(required = true) Integer id) {
        return ventaService.listarPorId(id)
                .map(venta -> new ResponseEntity<>(venta, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(required = true) Integer id) {
        if (ventaService.listarPorId(id).isPresent()) {
            ventaService.eliminarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}