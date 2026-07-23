package com.rosin.insurance.repository;

import com.rosin.insurance.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,UUID> {

    //find the vehicle using registrationNumber
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
    //find the vehicle using the chassisNumber
    Optional<Vehicle>findByChassisNumber(String chassisNumber);
    //find the cars under one client
    List<Vehicle>findByClientId(UUID clientId);
    //find an existing registrationNumber and chassisNumber during vehicle creation
    boolean existsByRegistrationNumber(String registrationNumber);
    boolean existsByChassisNumber(String chassisNumber);

}
