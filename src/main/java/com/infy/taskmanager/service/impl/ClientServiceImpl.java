package com.infy.taskmanager.service.impl;

import com.infy.taskmanager.constant.TaskConstants;
import com.infy.taskmanager.entity.Client;
import com.infy.taskmanager.entity.Country;
import com.infy.taskmanager.exception.CustomerAlreadyExistsException;
import com.infy.taskmanager.exception.ResourceNotFoundException;
import com.infy.taskmanager.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl {

    @Autowired
    private ClientRepository clientRepository;
    public void createRecord(Client client) {
        String cName = client.getClientLocationName().trim();
        Client client1 = clientRepository.findByClientLocationName(cName);
        if (client1 != null) {
            throw new CustomerAlreadyExistsException("Client Already Exist");
        }
        clientRepository.save(client);
    }

    public Client getRecord(String id) {
        Client client = clientRepository.findById(Integer.parseInt(id)).orElseThrow(
                () -> {
                    return new ResourceNotFoundException("Client", "ClientID", id);
                }
        );
        return client;
    }
    public List<Client> getAllRecord() {
        List<Client> clients= (List<Client>) clientRepository.findAll();
        return clients;
    }
}
