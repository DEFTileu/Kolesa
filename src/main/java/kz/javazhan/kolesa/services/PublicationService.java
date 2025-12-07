package kz.javazhan.kolesa.services;
import kz.javazhan.kolesa.repositories.PublicationRepository;
import kz.javazhan.kolesa.entities.Publication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kz.javazhan.kolesa.entities.Files;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PublicationService {
    private PublicationRepository publicationRepository;

    public Publication addImage(Publication publication, Files file) {
        List<Files> files = publication.getImages();
        files.add(file);
        publication.setImages(files);
        return publicationRepository.save(publication);
    }
}
