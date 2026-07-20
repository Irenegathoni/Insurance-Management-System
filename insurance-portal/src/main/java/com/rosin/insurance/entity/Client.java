package com.rosin.insurance.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @UuidGenerator
    @Column(updatable = false, nullable=false)
    private UUID id;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="second_name")
    private String secondName;

    @Column(name = "surname",nullable = false)
    private String surname;

    @Column(name="national_id",unique = true)
    private String nationalId;

    @Column(name = "kra_pin",unique = true)
    private String kraPin;

    @Column(name="phone_number",unique = true)
    private String phoneNumber;

    @CreationTimestamp
    @Column(name="created_at",updatable = false,nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id",nullable = false)
    private Agent agent;


}
