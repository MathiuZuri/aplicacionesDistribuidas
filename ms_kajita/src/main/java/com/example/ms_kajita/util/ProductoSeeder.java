package com.example.ms_kajita.util;

import com.example.ms_kajita.entity.Producto;
import com.example.ms_kajita.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductoSeeder implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    public ProductoSeeder(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {
        // Verificamos si ya existen datos para no duplicar
        if (productoRepository.count() == 0) {
            Producto prod1 = new Producto(null, "Laptop Dell XPS 13", "Laptop ultraligera con procesador Intel Core i7", LocalDateTime.now(), 1, null); // categoriaId = 1
            Producto prod2 = new Producto(null, "Camiseta Algodón Hombre", "Camiseta de algodón 100% para hombre, varios colores", LocalDateTime.now(), 2, null); // categoriaId = 2
            Producto prod3 = new Producto(null, "Aspiradora Robot", "Aspiradora robot inteligente con mapeo láser", LocalDateTime.now(), 3, null); // categoriaId = 3
            Producto prod4 = new Producto(null, "LEGO Star Wars Millennium Falcon", "Set de construcción LEGO de la nave Millennium Falcon", LocalDateTime.now(), 4, null); // categoriaId = 4
            Producto prod5 = new Producto(null, "Cien años de soledad", "Libro de Gabriel García Márquez", LocalDateTime.now(), 5, null); // categoriaId = 5
            Producto prod6 = new Producto(null, "Balón de Fútbol Adidas", "Balón de fútbol profesional Adidas", LocalDateTime.now(), 6, null); // categoriaId = 6
            Producto prod7 = new Producto(null, "Vitaminas C 1000mg", "Suplemento de vitamina C para el sistema inmunológico", LocalDateTime.now(), 7, null); // categoriaId = 7
            Producto prod8 = new Producto(null, "Labial Mate", "Labial mate de larga duración, varios tonos", LocalDateTime.now(), 8, null); // categoriaId = 8
            Producto prod9 = new Producto(null, "Aceite de Motor Sintético", "Aceite de motor sintético para autos", LocalDateTime.now(), 9, null); // categoriaId = 9
            Producto prod10 = new Producto(null, "Comida para Gatos", "Comida premium para gatos adultos", LocalDateTime.now(), 10, null); // categoriaId = 10
            Producto prod11 = new Producto(null, "Taladro Inalámbrico", "Taladro inalámbrico con batería de litio", LocalDateTime.now(), 11, null); // categoriaId = 11
            Producto prod12 = new Producto(null, "Teclado Mecánico Gaming", "Teclado mecánico para juegos con retroiluminación RGB", LocalDateTime.now(), 12, null); // categoriaId = 12
            Producto prod13 = new Producto(null, "Smartphone Samsung Galaxy S23", "Smartphone con cámara de alta resolución", LocalDateTime.now(), 13, null); // categoriaId = 13
            Producto prod14 = new Producto(null, "Vinilo The Beatles Abbey Road", "Vinilo del álbum Abbey Road de The Beatles", LocalDateTime.now(), 14, null); // categoriaId = 14
            Producto prod15 = new Producto(null, "Kit de Herramientas de Jardinería", "Kit de herramientas para jardinería", LocalDateTime.now(), 15, null); // categoriaId = 15
            Producto prod16 = new Producto(null, "Pasta Italiana Integral", "Pasta italiana integral de trigo duro", LocalDateTime.now(), 16, null); // categoriaId = 16
            Producto prod17 = new Producto(null, "Jugo de Naranja Natural", "Jugo de naranja natural sin conservantes", LocalDateTime.now(), 17, null); // categoriaId = 17
            Producto prod18 = new Producto(null, "Refrigerador Smart", "Refrigerador inteligente con pantalla táctil", LocalDateTime.now(), 18, null); // categoriaId = 18
            Producto prod19 = new Producto(null, "Anillo de Compromiso Diamante", "Anillo de compromiso con diamante certificado", LocalDateTime.now(), 19, null); // categoriaId = 19
            Producto prod20 = new Producto(null, "Consola PlayStation 5", "Consola de videojuegos PlayStation 5", LocalDateTime.now(), 20, null); // categoriaId = 20

            productoRepository.save(prod1);
            productoRepository.save(prod2);
            productoRepository.save(prod3);
            productoRepository.save(prod4);
            productoRepository.save(prod5);
            productoRepository.save(prod6);
            productoRepository.save(prod7);
            productoRepository.save(prod8);
            productoRepository.save(prod9);
            productoRepository.save(prod10);
            productoRepository.save(prod11);
            productoRepository.save(prod12);
            productoRepository.save(prod13);
            productoRepository.save(prod14);
            productoRepository.save(prod15);
            productoRepository.save(prod16);
            productoRepository.save(prod17);
            productoRepository.save(prod18);
            productoRepository.save(prod19);
            productoRepository.save(prod20);

            System.out.println("Datos de productos insertados correctamente.");
        } else {
            System.out.println("Los productos ya existen, no se insertaron datos.");
        }
    }
}