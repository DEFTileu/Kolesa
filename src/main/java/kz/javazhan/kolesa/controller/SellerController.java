package kz.javazhan.kolesa.controller;


import kz.javazhan.kolesa.entities.DTO.PublicationDTO;
import kz.javazhan.kolesa.entities.DTO.SellerDTO;
import kz.javazhan.kolesa.entities.Publication;
import kz.javazhan.kolesa.entities.Seller;
import kz.javazhan.kolesa.entities.User;
import kz.javazhan.kolesa.mappers.PublicationEntityMapper;
import kz.javazhan.kolesa.mappers.SellerMapper;
import kz.javazhan.kolesa.services.PublicationEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;
    private final SellerMapper sellerMapper;
    private final PublicationEntityMapper publicationMapper;
    private final PublicationEntityService publicationService;

    @GetMapping
    public List<PublicationDTO> getAllPublications() {
        return publicationService.getAllPublications()
                .stream()
                .map(publicationMapper::toPublicationDTO)
                .toList();
    }

    @GetMapping("/info")
    public SellerDTO getSellerInfo(@AuthenticationPrincipal User user){
        Seller seller = sellerService.getOrCreateSeller(user);
        return sellerMapper.toSellerDTO(seller);
    }

    @PostMapping
    public PublicationDTO createPublication(@AuthenticationPrincipal User user, @RequestBody PublicationDTO dto){
        Publication publication = sellerService.createPublication(user, dto);
        return publicationMapper.toPublicationDTO(publication);
    }
}
