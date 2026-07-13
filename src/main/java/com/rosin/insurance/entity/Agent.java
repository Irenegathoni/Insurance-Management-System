package com.rosin.insurance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name ="agents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agent {

    @Id
    @UuidGenerator
    @Column(updatable = false,nullable = false)
    private  UUID id;

    @Column(nullable=false,unique = true)
    private String username;

    @Column(nullable = false,unique=true)
    private String email;

    @Column(name="password_hash",nullable = false)
    private String passwordHash;

    @Column(name="phone_number",unique = true)
    private String phoneNumber;

    @CreationTimestamp
    @Column(name="created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "agent",fetch = FetchType.LAZY)
    private List<Client> clients;

}
