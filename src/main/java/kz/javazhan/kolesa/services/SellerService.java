package kz.javazhan.kolesa.services;

import kz.javazhan.kolesa.entities.DTO.PublicationDTO;
import kz.javazhan.kolesa.entities.Publication;
import kz.javazhan.kolesa.entities.Seller;
import kz.javazhan.kolesa.entities.User;
import kz.javazhan.kolesa.entities.enums.UserRole;
import kz.javazhan.kolesa.repositories.PublicationRepository;
import kz.javazhan.kolesa.repositories.SellerRepository;
import kz.javazhan.kolesa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;
    private final UserService userService;
    private final PublicationRepository publicationRepository;

//    public Seller getOrCreateSeller(User user){
//        return sellerRepository.findSellerByUsername(user.getUsername())
//                .orElseGet(() -> {
//                    Seller seller = Seller.builder()
//                            .user(user)
//                            .build();
//                    return sellerRepository.save(seller);
//                });
//    }

//    public Publication createPublication(User user, PublicationDTO dto) {
//        Seller seller = getOrCreateSeller(user);
//
//        Publication publication = seller.createPublication(
//                dto.getTitle(),
//                dto.getDescription(),
//                dto.getContent(),
//                dto.getImages());
//
//        return publicationRepository.save(publication);
//    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller createSeller(User user) {
        Seller seller = sellerRepository.findByUser(user);
        if (seller != null){
            throw new RuntimeException("Your already seller");
        }
        seller = Seller.builder()
                .user(user)
                .build();
        user.setRole(UserRole.ROLE_SELLER);
        userService.save(user);
        return sellerRepository.save(seller);
    }
}
