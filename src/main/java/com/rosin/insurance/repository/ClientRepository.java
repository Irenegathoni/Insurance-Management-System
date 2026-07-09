package com.rosin.insurance.repository;

import com.rosin.insurance.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClientRepository extends JpaRepository<Client,UUID>{

    //find the client by their nationalId
    Optional<Client> findByNationalId(String nationalId);

    //find the client by the surname and ignore cases
    List<Client> findBySurnameContainingIgnoreCase(String surname);

    //find the client by any name, whatever case you write, it is first lowered to the lower case(LOWER(...)LIKE LOWER(...)
    //concat('%',:name,'%') means finding that name anywhere in the value
    //OR — if it matches ANY of the three name fields, include that client in results.
    @Query("SELECT c FROM Client c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
            "LOWER(c.secondName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
            "LOWER(c.surname) LIKE LOWER(CONCAT('%', :name, '%'))")
    //:name — this is a named parameter. @Param("name") connects it to the method argument String name.
    List<Client> searchByAnyName(@Param("name") String name);

    //find the clients under the agent
    List<Client> findByAgentId(UUID agentId);



}
