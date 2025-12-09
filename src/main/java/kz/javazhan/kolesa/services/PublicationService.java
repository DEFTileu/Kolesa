package kz.javazhan.kolesa.services;
import kz.javazhan.kolesa.entities.DTO.PublicationDTO;
import kz.javazhan.kolesa.entities.Seller;
import kz.javazhan.kolesa.entities.User;
import kz.javazhan.kolesa.mappers.PublicationMapper;
import kz.javazhan.kolesa.repositories.PublicationRepository;
import kz.javazhan.kolesa.entities.Publication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import kz.javazhan.kolesa.repositories.SellerRepository;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final SellerRepository sellerRepository;
    private final PublicationMapper publicationMapper;
    private final UserService userService;


    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    public Publication getPublicationById(UUID publicationId) {
        return publicationRepository.findById(publicationId)
                .orElseThrow(() -> new RuntimeException("Publication with id not found "+publicationId));
    }

    public PublicationDTO getPublicationByUser(User user) {
        Seller seller = sellerRepository.findByUser(user);
        Publication publication =  publicationRepository.findByAuthor(seller);
        return publicationMapper.toPublicationDTO(publication);
    }

    public Publication save(Publication publication, User user) {
        Seller seller = sellerRepository.findByUser(user);
        publication.setAuthor(seller);
        return publicationRepository.save(publication);
    }

    public List<Publication> getPublicationsByUser(User user) {
        Seller seller = sellerRepository.findByUser(user);
         return publicationRepository.findAllByAuthor(seller);

    }

    public void deletePublication(UUID publicationId) throws Exception {
        User user = userService.getCurrentUser();
        Seller seller = sellerRepository.findByUser(user);
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new RuntimeException("Publication doesn't exits"));
        if (publication.getAuthor().getId() != seller.getId()){
            throw new RuntimeException("It's not your publication! You can't delete it");
        }
        publicationRepository.delete(publication);
    }
}
