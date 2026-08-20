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

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;
    private final DocumentRepository documentRepository;
    private final Cloudinary cloudinary;
}
