package com.example.ms_test01post.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.ms_test01post.dto.Cliente; // Importa el DTO
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ClienteServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private final String CLIENTE_SERVICE_ID = "ms-cliente-service";

    public Cliente obtenerClienteInfo(int clienteId) {
        // Usar LoadBalancerClient para obtener la instancia del servicio
        ServiceInstance serviceInstance = loadBalancerClient.choose(CLIENTE_SERVICE_ID);

        if (serviceInstance != null) {
            URI uri = UriComponentsBuilder.fromUri(serviceInstance.getUri())
                    .path("/clientes/")
                    .path(String.valueOf(clienteId))
                    .build()
                    .toUri();

            try {
                return restTemplate.getForObject(uri, Cliente.class);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.err.println("No se encontraron instancias disponibles para el servicio: " + CLIENTE_SERVICE_ID);
            return null;
        }
    }
}