package com.rosin.insurance.repository;

import com.rosin.insurance.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgentRepository extends JpaRepository<Agent,UUID>{

    //find the agent by email,during login
    //findby means:SELECT * FROM...WHERE
    Optional<Agent> findByEmail(String email);

    //check if the email already exists during registration
    //existby means: SELECT..WHERE
    boolean existsByEmail(String email);

    //check if the username is already taken during registration
    boolean existsByUsername(String username);
}
