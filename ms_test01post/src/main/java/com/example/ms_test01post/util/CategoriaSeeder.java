package com.example.ms_test01post.util;

import com.example.ms_test01post.entity.Venta;
import com.example.ms_test01post.repository.VentaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoriaSeeder implements CommandLineRunner {

    private final VentaRepository ventaRepository;

    public CategoriaSeeder(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public void run(String... args) {
        // Verificamos si ya existen datos para no duplicar
        if (ventaRepository.count() == 0) {
            Venta cat1 = new Venta(null, "Electrónica");
            Venta cat2 = new Venta(null, "Ropa");
            Venta cat3 = new Venta(null, "Hogar");
            Venta cat4 = new Venta(null, "Juguetes");
            Venta cat5 = new Venta(null, "Libros");
            Venta cat6 = new Venta(null, "Deportes");
            Venta cat7 = new Venta(null, "Salud");
            Venta cat8 = new Venta(null, "Belleza");
            Venta cat9 = new Venta(null, "Automotriz");
            Venta cat10 = new Venta(null, "Mascotas");
            Venta cat11 = new Venta(null, "Herramientas");
            Venta cat12 = new Venta(null, "Computadoras");
            Venta cat13 = new Venta(null, "Celulares");
            Venta cat14 = new Venta(null, "Música");
            Venta cat15 = new Venta(null, "Jardín");
            Venta cat16 = new Venta(null, "Alimentos");
            Venta cat17 = new Venta(null, "Bebidas");
            Venta cat18 = new Venta(null, "Electrodomésticos");
            Venta cat19 = new Venta(null, "Joyería");
            Venta cat20 = new Venta(null, "Videojuegos");

            ventaRepository.save(cat1);
            ventaRepository.save(cat2);
            ventaRepository.save(cat3);
            ventaRepository.save(cat4);
            ventaRepository.save(cat5);
            ventaRepository.save(cat6);
            ventaRepository.save(cat7);
            ventaRepository.save(cat8);
            ventaRepository.save(cat9);
            ventaRepository.save(cat10);
            ventaRepository.save(cat11);
            ventaRepository.save(cat12);
            ventaRepository.save(cat13);
            ventaRepository.save(cat14);
            ventaRepository.save(cat15);
            ventaRepository.save(cat16);
            ventaRepository.save(cat17);
            ventaRepository.save(cat18);
            ventaRepository.save(cat19);
            ventaRepository.save(cat20);

            System.out.println("Datos de categorías insertados correctamente.");
        } else {
            System.out.println("Las categorías ya existen, no se insertaron datos.");
        }
    }
}