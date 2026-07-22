package com.rosin.insurance.controller;
import com.rosin.insurance.entity.Client;
import com.rosin.insurance.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor

public class ClientController {
    private final ClientService clientService;

    //getting all clients
    //api/clients
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());

    }

    //getting clients by id
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    //getting clients under the agent
    @GetMapping("/byAgent/{agentId}")
    public ResponseEntity<List<Client>> getClientByAgentId(@PathVariable UUID agentId) {
        return ResponseEntity.ok(clientService.getClientByAgentId(agentId));
    }
    //searching by name
    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchByAnyName(@RequestParam String name){
        return ResponseEntity.ok(clientService.searchByAnyName(name));
    }

    //creating a client
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestParam UUID agentId,@RequestBody Client client) {
        Client created = clientService.createClient(agentId,client);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //updating a client
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable UUID id, @RequestBody Client client) {
        Client updated = clientService.updateClient(id, client);
        return ResponseEntity.ok(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}


