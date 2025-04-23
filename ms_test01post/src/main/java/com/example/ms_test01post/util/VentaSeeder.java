package com.example.ms_test01post.util;

import com.example.ms_test01post.entity.Venta;
import com.example.ms_test01post.repository.VentaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class VentaSeeder implements CommandLineRunner {

    private final VentaRepository ventaRepository;
    private final Random random = new Random();

    public VentaSeeder(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public void run(String... args) {
        // Verificamos si ya existen datos para no duplicar (puedes ajustar el criterio)
        if (ventaRepository.count() < 10) { // Insertamos al menos 10 ventas de ejemplo
            for (int i = 0; i < 20; i++) {
                Venta venta = new Venta();
                venta.setFechaVenta(LocalDateTime.now().minusDays(random.nextInt(30))); // Ventas de los últimos 30 días
                venta.setProductoId(random.nextInt(20) + 1); // Asumiendo que tienes hasta 20 productos (ajusta según tu necesidad)
                venta.setCantidad(random.nextInt(5) + 1); // Cantidades de 1 a 5
                ventaRepository.save(venta);
            }
            System.out.println("Datos de ventas insertados correctamente.");
        } else {
            System.out.println("Ya existen suficientes datos de ventas, no se insertaron más.");
        }
    }
}