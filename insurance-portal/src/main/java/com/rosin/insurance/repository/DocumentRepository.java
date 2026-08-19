package com.rosin.insurance.repository;
import com.rosin.insurance.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface DocumentRepository extends JpaRepository<Document,UUID> {
    //find all the documents under one client
    List<Document> findByClientId(UUID clientId);
    //find the vehicles' documents
    List<Document> findByVehicleId(UUID vehicleId);
    //find the document by its type
    List<Document> findByDocumentType(String documentType);
    //agent wants to know all the documents they uploaded
    List<Document> findByUploadedById(UUID uploadedBy);

}
