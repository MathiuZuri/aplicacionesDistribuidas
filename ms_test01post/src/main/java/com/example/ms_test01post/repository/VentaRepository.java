package com.example.ms_test01post.repository;

import com.example.ms_test01post.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
}