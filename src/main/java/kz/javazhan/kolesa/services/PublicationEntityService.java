package kz.javazhan.kolesa.services;
import kz.javazhan.kolesa.entities.DTO.PublicationEntityDTO;
import kz.javazhan.kolesa.entities.PublicationEntity;
import kz.javazhan.kolesa.entities.Seller;
import kz.javazhan.kolesa.entities.User;

import kz.javazhan.kolesa.mappers.PublicationEntityMapper;
import kz.javazhan.kolesa.repositories.PublicationEntityRepository;
import kz.javazhan.kolesa.state.PublicationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import kz.javazhan.kolesa.repositories.SellerRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor  // Dependency injection үшін
public class PublicationEntityService {


    private final PublicationEntityRepository publicationEntityRepository;
    private final SellerRepository sellerRepository;
    private final UserService userService;




    public List<PublicationEntity> getPublishedPublications() {
        return publicationEntityRepository.findAllByStatus(PublicationStatus.PUBLISHED);
    }


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

    public PublicationEntity publishPublication(UUID publicationId) {
        User user = userService.getCurrentUser();
        Seller seller = sellerRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Seller not Found"));
        PublicationEntity publication = publicationEntityRepository.findById(publicationId)
                .orElseThrow(() -> new RuntimeException("Publication doesn't exist"));

        if (!publication.getAuthor().getId().equals(seller.getId())) {
            throw new RuntimeException("It's not your publication! You can't publish it");
        }

        publication.publish();
        return publicationEntityRepository.save(publication);
    }

    public PublicationEntity sendToReview(UUID publicationId) {
        User user = userService.getCurrentUser();
        Seller seller = sellerRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Seller not Found"));
        PublicationEntity publication = publicationEntityRepository.findById(publicationId)
                .orElseThrow(() -> new RuntimeException("Publication doesn't exist"));

        if (!publication.getAuthor().getId().equals(seller.getId())) {
            throw new RuntimeException("It's not your publication! You can't send it to review");
        }

        publication.sendToReview();
        return publicationEntityRepository.save(publication);
    }

    public PublicationEntity archivePublication(UUID publicationId) {
        User user = userService.getCurrentUser();
        Seller seller = sellerRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Seller not Found"));
        PublicationEntity publication = publicationEntityRepository.findById(publicationId)
                .orElseThrow(() -> new RuntimeException("Publication doesn't exist"));

        if (!publication.getAuthor().getId().equals(seller.getId())) {
            throw new RuntimeException("It's not your publication! You can't archive it");
        }

        publication.archive();
        return publicationEntityRepository.save(publication);
    }

    public PublicationEntity rejectPublication(UUID publicationId) {
        User user = userService.getCurrentUser();
        // todo ұмытпа Админ тексеретін функция қосуды
        PublicationEntity publication = publicationEntityRepository.findById(publicationId)
                .orElseThrow(() -> new RuntimeException("Publication doesn't exist"));

        publication.reject();
        return publicationEntityRepository.save(publication);
    }

    public PublicationEntity editPublication(UUID publicationId, PublicationEntity publicationDetails, User user) {
        PublicationEntity publication = getPublicationById(publicationId);

        publication.setTitle(publicationDetails.getTitle());
        publication.setDescription(publicationDetails.getDescription());
        publication.setContent(publicationDetails.getContent());
        publication.setImages(publicationDetails.getImages());

        return save(publication, publication.getAuthor().getUser());
    }

    public PublicationEntity clonePublication(UUID publicationId, User user) {
        PublicationEntity originalPublication = getPublicationById(publicationId);
        Seller seller = sellerRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Seller not Found"));

        if (!originalPublication.getAuthor().getId().equals(seller.getId()) &&
            originalPublication.getStatus() != PublicationStatus.PUBLISHED) {
            throw new RuntimeException("You can only clone your own publications or published publications");
        }

        PublicationEntity clonedPublication = originalPublication.clone();

        clonedPublication.setTitle(originalPublication.getTitle() + " (Копия)");

        clonedPublication.setAuthor(seller);

        return publicationEntityRepository.save(clonedPublication);
    }
}