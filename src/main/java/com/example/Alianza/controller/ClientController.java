package com.example.Alianza.controller;


import com.example.Alianza.dto.ClientDTO;
import com.example.Alianza.entity.Client;
import com.example.Alianza.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /** Endpoint Consultar listado clientes  */
    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    /** Endpoint Crear cliente  */
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO  clientDTO) {
        logger.debug("Recibe request para crear cliente: {}", clientDTO);
        ClientDTO  createdClient = clientService.createClient(clientDTO);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public List<ClientDTO> searchClients(@RequestParam String sharedKey) {
        logger.debug("Recibe el request para consulta con sharedKey: {}", sharedKey);
        return clientService.searchClients(sharedKey);
    }
}