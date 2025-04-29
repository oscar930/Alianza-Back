package com.example.Alianza.repository;

import  com.example.Alianza.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findBySharedKeyContainingIgnoreCase(String sharedKey);
}