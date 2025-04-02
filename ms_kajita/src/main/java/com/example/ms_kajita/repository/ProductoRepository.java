package com.example.ms_kajita.repository;

import com.example.ms_kajita.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}