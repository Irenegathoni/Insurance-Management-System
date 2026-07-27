package com.rosin.insurance.service;

import com.rosin.insurance.entity.Client;
import com.rosin.insurance.entity.Vehicle;
import com.rosin.insurance.repository.ClientRepository;
import com.rosin.insurance.repository.VehicleRepository;
import java.util.UUID;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;

    //get all vehicles
    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    //get vehicle by id
    public Vehicle getVehicleById(UUID id){
        return vehicleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Vehicle not found by id:"+id));
    }

    //getting the vehicles under a client
    public List<Vehicle> getVehicleByClientId(UUID clientId){
        return vehicleRepository.findByClientId(clientId);
    }

    //create vehicle
    public Vehicle createVehicle(UUID clientId,Vehicle vehicle){
        //vehicle exists under a client
        Client client=clientRepository.findById(clientId)
                .orElseThrow(()->new RuntimeException("Client not found with this id:"+clientId));

        //check if the vehicle registration already exists
        if (vehicleRepository.existsByRegistrationNumber(vehicle.getRegistrationNumber())){
            throw new RuntimeException("Vehicle with this registration already exists:"+vehicle.getRegistrationNumber());
        }

        //check if the vehicle chassis number already exists
        if(vehicleRepository.existsByChassisNumber(vehicle.getChassisNumber())){
            throw  new RuntimeException("Vehicle with this chassis number already exists:"+vehicle.getChassisNumber());

        }
        //link vehicle with client
        vehicle.setClient(client);
        return vehicleRepository.save(vehicle);


    }
    //update vehicle
    public Vehicle updateVehicle(UUID id,Vehicle updatedVehicle){
        //check if the vehicle exists using its id
        Vehicle existing=vehicleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Vehicle not found by id:"+id));
        existing.setRegistrationNumber(updatedVehicle.getRegistrationNumber());
        existing.setMake(updatedVehicle.getMake());
        existing.setModel(updatedVehicle.getModel());
        existing.setYear(updatedVehicle.getYear());
        existing.setChassisNumber(updatedVehicle.getChassisNumber());
        return  vehicleRepository.save(existing);

    }

    //delete vehicle
    public void deleteVehicle(UUID id){
        //check if the vehicle exists by id
        vehicleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Vehicle not found by id:"+id));
        vehicleRepository.deleteById(id);

    }
}
