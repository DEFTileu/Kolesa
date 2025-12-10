package kz.javazhan.kolesa.services;
import kz.javazhan.kolesa.entities.DTO.PublicationEntityDTO;
import kz.javazhan.kolesa.entities.PublicationEntity;
import kz.javazhan.kolesa.entities.Seller;
import kz.javazhan.kolesa.entities.User;

import kz.javazhan.kolesa.mappers.PublicationEntityMapper;
import kz.javazhan.kolesa.repositories.PublicationEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import kz.javazhan.kolesa.repositories.SellerRepository;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PublicationEntityService {
    private final PublicationEntityRepository publicationEntityRepository;
    private final SellerRepository sellerRepository;
    private final PublicationEntityMapper publicationMapper;
    private final UserService userService;


    public List<PublicationEntity> getAllPublications() {
        return publicationEntityRepository.findAll();
    }

//    public PublicationEntityDTO getPublicationByUser(User user) {
//        Seller seller = sellerRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Seller not found"));
//        PublicationEntity publication =  publicationEntityRepository.findByAuthor(seller);
//        return publicationMapper.toPublicationEntityDTO(publication);
//    }

    public PublicationEntity save(PublicationEntity publication, User user) {
        Seller seller = sellerRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Seller not Found"));
        publication.setAuthor(seller);
        return publicationEntityRepository.save(publication);
    }

    public List<PublicationEntity> getPublicationsByUser(User user) {
        Seller seller = sellerRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Seller not Found"));
        return publicationEntityRepository.findAllByAuthor(seller);

    }

    public void deletePublication(UUID publicationId) throws Exception {
        User user = userService.getCurrentUser();
        Seller seller = sellerRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Seller not Found"));
        PublicationEntity publication = publicationEntityRepository.findById(publicationId)
                .orElseThrow(() -> new RuntimeException("Publication doesn't exits"));
        if (publication.getAuthor().getId() != seller.getId()){
            throw new RuntimeException("It's not your publication! You can't delete it");
        }
        publicationEntityRepository.delete(publication);
    }



    public PublicationEntity getPublicationById(UUID publicationId) {
        return publicationEntityRepository.findById(publicationId)
                .orElseThrow(() -> new RuntimeException("Publication with id not found "+publicationId));
    }
}