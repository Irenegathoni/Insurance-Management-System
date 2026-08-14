package com.rosin.insurance.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name="documents")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Document {
    @Id
    @UuidGenerator
    @Column(updatable = false,nullable = false)
    private  UUID id;

    //FK to Client
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="client_id",nullable = false)
    private Client client;

    //FK to Agent , to let it be known who uploaded the document
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="uploaded_by",nullable = false)
    private Agent uploadedBy;

    //FK to Vehicle, it is nullable because the only logbook belongs to vehicle, the other documents belong to the client as a person
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_id",nullable = true)
    private Vehicle vehicle;

    @Column(name="file_name",nullable = false)
    private String fileName;

    @Column(name="storage_key",nullable=false)
    private String storageKey;

    @Column(name="mime_type",nullable = false)
    private String mimeType;

    @Column(name = "size_byte",nullable = false)
    private int sizeByte;

    @Column(name="document_type",nullable = false)
    private String documentType;

    @CreationTimestamp
    @Column(name="uploaded_at",nullable = false,updatable = false)
    private LocalDateTime uploadedAt;
}
