package com.example.Alianza.controller;

import com.example.Alianza.dto.ClientDTO;
import com.example.Alianza.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
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
        List<ClientDTO> clients = Arrays.asList(clientDTO);

        when(clientService.getAllClients()).thenReturn(clients);

        List<ClientDTO> response = clientController.getAllClients();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("sharedKey123", response.get(0).getSharedKey());
        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void testCreateClient() {
        when(clientService.createClient(any(ClientDTO.class))).thenReturn(clientDTO);

        ResponseEntity<ClientDTO> response = clientController.createClient(clientDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("sharedKey123", response.getBody().getSharedKey());
        verify(clientService, times(1)).createClient(any(ClientDTO.class));
    }

    @Test
    void testSearchClients() {
        List<ClientDTO> clients = Arrays.asList(clientDTO);

        when(clientService.searchClients("sharedKey123")).thenReturn(clients);

        List<ClientDTO> response = clientController.searchClients("sharedKey123");

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("sharedKey123", response.get(0).getSharedKey());
        verify(clientService, times(1)).searchClients("sharedKey123");
    }
}