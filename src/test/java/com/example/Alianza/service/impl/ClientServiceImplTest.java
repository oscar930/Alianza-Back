package com.example.Alianza.service.impl;

import com.example.Alianza.dto.ClientDTO;
import com.example.Alianza.entity.Client;
import com.example.Alianza.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;
    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        client = new Client();
        client.setSharedKey("sharedKey123");
        client.setBusinessId("businessId123");
        client.setEmail("test@example.com");
        client.setPhone("1234567890");
        client.setStartDate(LocalDate.of(2023, 1, 1));
        client.setEndDate(LocalDate.of(2025, 1, 1));

        clientDTO = new ClientDTO(
                "sharedKey123",
                "businessId123",
                "test@example.com",
                "1234567890",
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2025, 1, 1)
        );
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client));

        List<ClientDTO> result = clientService.getAllClients();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("sharedKey123", result.get(0).getSharedKey());

        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testCreateClientFromDTO() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDTO saved = clientService.createClient(clientDTO);

        assertNotNull(saved);
        assertEquals("sharedKey123", saved.getSharedKey());

        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testCreateClientFromEntity() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client saved = clientService.createClient(client);

        assertNotNull(saved);
        assertEquals("sharedKey123", saved.getSharedKey());

        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testSearchClients() {
        when(clientRepository.findBySharedKeyContainingIgnoreCase("sharedKey"))
                .thenReturn(Arrays.asList(client));

        List<ClientDTO> result = clientService.searchClients("sharedKey");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("sharedKey123", result.get(0).getSharedKey());

        verify(clientRepository, times(1)).findBySharedKeyContainingIgnoreCase("sharedKey");
    }
}