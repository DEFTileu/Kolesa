package kz.javazhan.kolesa.services;
import kz.javazhan.kolesa.entities.PublicationEntity;
import kz.javazhan.kolesa.repositories.PublicationEntityRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PublicationEntityService {
    private final PublicationEntityRepository publicationEntityRepository;


    public List<PublicationEntity> getAllPublications() {
        return publicationEntityRepository.findAll();
    }

    public PublicationEntity getPublicationById(UUID publicationId) {
        return publicationEntityRepository.findById(publicationId)
                .orElseThrow(() -> new RuntimeException("Publication with id not found "+publicationId));
    }
}
