package com.example.Alianza.service.impl;

import com.example.Alianza.dto.ClientDTO;
import com.example.Alianza.entity.Client;
import com.example.Alianza.exception.InvalidDataException;
import com.example.Alianza.exception.ResourceNotFoundException;
import com.example.Alianza.repository.ClientRepository;
import com.example.Alianza.service.ClientService;
import org.flywaydb.core.internal.util.StringUtils;
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
        List<Client> clients = clientRepository.findAll();

        if(clients.isEmpty()) {
            logger.warn("No se encontraron clientes en la base de datos");
            throw new ResourceNotFoundException("No se encontraron clientes");
        }

        return clients.stream()
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

        if(!StringUtils.hasText(sharedKey)) {
            logger.error("El parámetro sharedKey no puede estar vacío");
            throw new InvalidDataException("El parámetro sharedKey es requerido");
        }
        logger.info("Buscar Cliente con sharedKey: {}", sharedKey);
        List<Client> clients = clientRepository.findBySharedKeyContainingIgnoreCase(sharedKey);

        if(clients.isEmpty()) {
            logger.warn("No se encontraron clientes con sharedKey que contenga: {}", sharedKey);
            throw new ResourceNotFoundException("No se encontraron clientes con el criterio de búsqueda");
        }

        return clients.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ClientDTO> searchClientsAll(String sharedKey, String businessId, String email, String phone, String startDate, String endDate) {
        List<Client> filteredClients = clientRepository.findByFilters(sharedKey, businessId, email, phone, startDate, endDate);
        return filteredClients.stream().map(this::convertToDTO).toList();
    }

    private ClientDTO convertToDTO(Client client) {
        if(client == null) {
            throw new InvalidDataException("El cliente no puede ser nulo para la conversión");
        }
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
        if(dto == null) {
            throw new InvalidDataException("El DTO no puede ser nulo para la conversión");
        }

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
