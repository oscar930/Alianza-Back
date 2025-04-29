package com.example.Alianza.service.impl;

import com.example.Alianza.dto.ClientDTO;
import com.example.Alianza.entity.Client;
import com.example.Alianza.repository.ClientRepository;
import com.example.Alianza.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClientServiceImpl implements ClientService {


    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        logger.info("Consultar todos los clientes");
        return clientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = convertToEntity(clientDTO);
        Client saved = clientRepository.save(client);
        return convertToDTO(saved);
    }

    public Client createClient(Client client) {
        logger.info("Crear Cliente con sharedKey: {}", client.getSharedKey());
        return clientRepository.save(client);
    }

    public List<ClientDTO> searchClients(String sharedKey) {
        logger.info("Buscar Cliente con sharedKey: {}", sharedKey);
        return clientRepository.findBySharedKeyContainingIgnoreCase(sharedKey).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ClientDTO convertToDTO(Client client) {
        return new ClientDTO(
                client.getSharedKey(),
                client.getBusinessId(),
                client.getEmail(),
                client.getPhone(),
                client.getStartDate(),
                client.getEndDate()
        );
    }

    private Client convertToEntity(ClientDTO dto) {
        Client client = new Client();
        client.setSharedKey(dto.getSharedKey());
        client.setBusinessId(dto.getBusinessId());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setStartDate(dto.getStartDate());
        client.setEndDate(dto.getEndDate());
        return client;
    }



}
