package com.infy.taskmanager.controller;

import com.infy.taskmanager.constant.TaskConstants;
import com.infy.taskmanager.dto.ResponseDto;
import com.infy.taskmanager.entity.Client;

import com.infy.taskmanager.service.impl.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientControlller {

    @Autowired
    private ClientServiceImpl clientService;

    @PostMapping("/createClient")
    public ResponseEntity<ResponseDto> createUser(@RequestBody @Valid Client client) {
        clientService.createRecord(client);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(TaskConstants.STATUS_201, TaskConstants.MESSAGE_201));
    }
     @GetMapping("/getClients")
    public ResponseEntity<List<Client>> getAllCountries() {
        List <Client> clients = (List<Client>) clientService.getAllRecord();
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

}
