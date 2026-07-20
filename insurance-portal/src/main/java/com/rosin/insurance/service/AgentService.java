package com.rosin.insurance.service;

import com.rosin.insurance.entity.Agent;
import com.rosin.insurance.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
//this generates a constructor for final fields
@RequiredArgsConstructor
public class AgentService {
    //this field shows that the AgentRepository cannot be replaced or reassigned
    private final AgentRepository agentRepository;

    //get all agents
    public List<Agent> getAllAgents(){
        return agentRepository.findAll();
    }

    //get agent by id
    public Agent getAgentById(UUID id){
        return agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found by id: "+id));

    }

    //creating an agent
    public Agent createAgent(Agent agent){
        //check if the agent exist by email
        if(agentRepository.existsByEmail(agent.getEmail())){
            throw  new RuntimeException("Email already registered:" +agent.getEmail());
        }
        //check if agent exist by username
        if(agentRepository.existsByUsername(agent.getUsername())){
            throw new RuntimeException("Username already registered:"+agent.getUsername());
        }
        return agentRepository.save(agent);

    }

    //updating the agent
    public Agent updateAgent(UUID id,Agent updatedAgent){
        //finding the agent by id
        Agent existing= agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found by id:"+ id));
        existing.setUsername(updatedAgent.getUsername());
        existing.setEmail(updatedAgent.getEmail());
        //todo : hash the password before saving it which will be done when bcrypt will be done later
        existing.setPasswordHash(updatedAgent.getPasswordHash());
        existing.setPhoneNumber(updatedAgent.getPhoneNumber());

        return agentRepository.save(existing);

    }

    //deleting an agent
    public void deleteAgent(UUID id){
        //finding agent by id
        agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found by id:" +id));
        agentRepository.deleteById(id);
    }


}
