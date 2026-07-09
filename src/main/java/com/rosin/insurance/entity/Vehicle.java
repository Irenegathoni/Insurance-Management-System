package com.rosin.insurance.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {

    @Id
    
    private UUID id;

}