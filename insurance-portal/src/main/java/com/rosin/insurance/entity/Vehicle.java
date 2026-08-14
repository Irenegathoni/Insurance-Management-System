package com.rosin.insurance.entity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="vehicles")

public class Vehicle {
    @UuidGenerator
    @Id
    @Column(updatable = false,nullable = false)
    private UUID id;

    @Column(name="registration_number",nullable = false,unique = true)
    private String registrationNumber;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    @Column(name="chassis_number",nullable = false,unique = true)
    private String chassisNumber;

    @CreationTimestamp
    @Column(name="created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    //relationship between vehicles and documents
    @OneToMany(mappedBy = "vehicle",fetch = FetchType.LAZY)
    private List <Document> documents;

   //relationship between vehicles and clients:many vehicles can belong to one client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id",nullable = false)
    private Client client;

}
