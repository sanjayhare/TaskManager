package com.infy.taskmanager.repository;

import com.infy.taskmanager.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    public Client findByClientLocationName(String cName);
}
