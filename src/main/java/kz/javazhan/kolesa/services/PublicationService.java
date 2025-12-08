package kz.javazhan.kolesa.services;
import kz.javazhan.kolesa.entities.DTO.PublicationDTO;
import kz.javazhan.kolesa.repositories.PublicationRepository;
import kz.javazhan.kolesa.entities.Publication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationRepository publicationRepository;


    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    public Publication getPublicationById(UUID publicationId) {
        return publicationRepository.findById(publicationId)
                .orElseThrow(() -> new RuntimeException("Publication with id not found "+publicationId));
    }
}
