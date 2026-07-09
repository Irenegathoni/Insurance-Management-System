package com.rosin.insurance.service;
import com.rosin.insurance.entity.Client;
import com.rosin.insurance.repository.ClientRepository;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    //get all clients
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }
    //get all clients by id
    public Client getClientById(UUID id){
        return  clientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Client not found by id:" +id));

    }

    //get clients under the agent
    public List<Client> getClientByAgentId(UUID agentId) {
       return clientRepository.findByAgentId(agentId);
    }

    //search client by any name
    public List<Client> searchByAnyName(String name){
        return clientRepository.searchByAnyName(name);
    }

    //create a new client-find if the client exists first by national id
    public Client createClient(Client client){
        //check if the client exists by their national id, if they do, throw a runtime exception error
        if(clientRepository.existsByNationalId(client.getNationalId())){
            throw  new RuntimeException("Client with this National Id already exists:" + client.getNationalId());
        }
        return clientRepository.save(client);

    }
    //update the client
    public Client updateClient(UUID id,Client updatedClient){
        //check if the client exists by id
        Client existing= clientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Client not found by id:"+ id));
        existing.setFirstName(updatedClient.getFirstName());
        existing.setSecondName(updatedClient.getSecondName());
        existing.setSurname(updatedClient.getSurname());
        existing.setNationalId(updatedClient.getNationalId());
        existing.setKraPin(updatedClient.getKraPin());
        existing.setPhoneNumber(updatedClient.getPhoneNumber());
        return clientRepository.save(existing);
    }
    //delete the client by id- check if the client exists first
    public void deleteClient(UUID id) {
        clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with this id:" + id));
        clientRepository.deleteById(id);
    }
}





