package com.rosin.insurance.controller;
import com.rosin.insurance.entity.Agent;
import com.rosin.insurance.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
//@restcontroller is an annotation for restful web services that returns data directly
@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentController {
    private final AgentService agentService;
    //getting all agents
    //GET/api/agents
    @GetMapping
    public ResponseEntity<List<Agent>> getAllAgents(){
        return ResponseEntity.ok(agentService.getAllAgents());
    }

    //getting by id
    //GET/api/agents/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Agent>getAgentById(@PathVariable UUID id){
        return ResponseEntity.ok(agentService.getAgentById(id));
    }
    //creating an agent
    //POST/api/agent
    @PostMapping
    public ResponseEntity<Agent> createAgent(@RequestBody Agent agent){
        Agent created=agentService.createAgent(agent);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //updating an agent
    //PUT/api/agent
    @PutMapping
    public ResponseEntity<Agent> updateAgent(@PathVariable UUID id,@RequestBody Agent agent){
        Agent updated=agentService.updateAgent(id,agent);
        return ResponseEntity.ok(updated);

    }

    //deleting an agent
    @DeleteMapping
    public ResponseEntity<Void> deleteAgent(@PathVariable UUID id){
        agentService.deleteAgent(id);
        return ResponseEntity.noContent().build();
    }


}
