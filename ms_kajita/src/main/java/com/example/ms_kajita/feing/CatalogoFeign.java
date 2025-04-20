package com.example.ms_kajita.feing;

import com.example.ms_kajita.dto.CategoriaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-catalogo-service", path = "/categorias")

public interface CatalogoFeign {

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarCategoria(@PathVariable Integer id);
}