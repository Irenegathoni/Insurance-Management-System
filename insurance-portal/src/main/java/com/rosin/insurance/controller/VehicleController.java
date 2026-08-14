package com.rosin.insurance.controller;

import com.rosin.insurance.entity.Vehicle;
import com.rosin.insurance.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    //get all vehicles
    @GetMapping
    public ResponseEntity <List<Vehicle>> getAllVehicle(){
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }


    //get vehicle by id
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable UUID id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    //vehicle under a client
    @GetMapping("/byClient/{clientId}")
    public ResponseEntity<List<Vehicle>> getVehicleByClientId(@PathVariable UUID clientId){
        return ResponseEntity.ok(vehicleService.getVehicleByClientId(clientId));
    }

    //create vehicle
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestParam UUID clientId,@RequestBody Vehicle vehicle) {
        Vehicle created = vehicleService.createVehicle(clientId, vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //update vehicle
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable UUID id,@RequestBody Vehicle vehicle){
        Vehicle updated=vehicleService.updateVehicle(id, vehicle);
        return ResponseEntity.ok(updated);
    }

    //delete vehicle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable UUID id){
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
