package com.rosin.insurance.service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.rosin.insurance.entity.Agent;
import com.rosin.insurance.entity.Client;
import com.rosin.insurance.entity.Vehicle;
import com.rosin.insurance.entity.Document;
import com.rosin.insurance.repository.AgentRepository;
import com.rosin.insurance.repository.ClientRepository;
import com.rosin.insurance.repository.VehicleRepository;
import com.rosin.insurance.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;
    private final DocumentRepository documentRepository;
    private final Cloudinary cloudinary;

    //upload method
    public Document uploadDocument(UUID agentId, UUID clientId,
                                   UUID vehicleId, String documentType, MultipartFile file)
            throws IOException {
        //VALIDATE FILE TYPE
        //Only allow PDF,JPEG AND PNG files
        List<String> allowedTypes = Arrays.asList("application/pdf", "image/jpeg", "image/png");

        if (!allowedTypes.contains(file.getContentType())) {
            throw new RuntimeException("File type not allowed.Only PDF,JPEG and PNG accepted.");
        }


        //validate file size
        //max file size is 5MB(5 * 1024 *1024 BYTES)
        long maxSize = 5 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            throw new RuntimeException("File too large .Maximum size is 5MB.");
        }

    }


}
