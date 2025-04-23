package com.example.ms_test01post.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.ms_test01post.dto.Cliente; // Importa el DTO

@Service
public class ClienteServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String clienteServiceUrl = "http://ms-cliente-service"; // Nombre del servicio C# en Eureka

    public Cliente obtenerClienteInfo(int clienteId) {
        String url = clienteServiceUrl + "/clientes/" + clienteId;
        try {
            return restTemplate.getForObject(url, Cliente.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}