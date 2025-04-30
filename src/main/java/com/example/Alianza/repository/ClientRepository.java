package com.example.Alianza.repository;

import  com.example.Alianza.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findBySharedKeyContainingIgnoreCase(String sharedKey);

    @Query("SELECT c FROM Client c " +
            "WHERE (:sharedKey IS NULL OR LOWER(c.sharedKey) LIKE LOWER(CONCAT('%', :sharedKey, '%'))) " +
            "AND (:businessId IS NULL OR LOWER(c.businessId) LIKE LOWER(CONCAT('%', :businessId, '%'))) " +
            "AND (:email IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
            "AND (:phone IS NULL OR LOWER(c.phone) LIKE LOWER(CONCAT('%', :phone, '%'))) " +
            "AND (:startDate IS NULL OR c.startDate = :startDate) " +
            "AND (:endDate IS NULL OR c.endDate = :endDate)")
    List<Client> findByFilters(
            @Param("sharedKey") String sharedKey,
            @Param("businessId") String businessId,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);


}