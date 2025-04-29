package com.example.Alianza.service;

import com.example.Alianza.dto.ClientDTO;
import com.example.Alianza.entity.Client;
import com.example.Alianza.repository.ClientRepository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public interface  ClientService {

    List<ClientDTO> getAllClients();
    ClientDTO createClient(ClientDTO clientDTO);
    List<ClientDTO> searchClients(String sharedKey);

}
