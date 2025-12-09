package kz.javazhan.kolesa.controller;


import kz.javazhan.kolesa.entities.DTO.PublicationEntityDTO;
import kz.javazhan.kolesa.entities.DTO.SellerDTO;
import kz.javazhan.kolesa.entities.PublicationEntity;
import kz.javazhan.kolesa.entities.Seller;
import kz.javazhan.kolesa.entities.User;
import kz.javazhan.kolesa.mappers.PublicationEntityMapper;
import kz.javazhan.kolesa.mappers.SellerMapper;
import kz.javazhan.kolesa.services.PublicationEntityService;
import kz.javazhan.kolesa.services.SellerService;
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
    public List<PublicationEntityDTO> getAllPublications() {
        return publicationService.getAllPublications()
                .stream()
                .map(publicationMapper::toPublicationEntityDTO)
                .toList();
    }

    @GetMapping("/info")
    public SellerDTO getSellerInfo(@AuthenticationPrincipal User user){
        Seller seller = sellerService.createSeller(user);
        return sellerMapper.toSellerDTO(seller);
    }

    @PostMapping
    public PublicationEntityDTO createPublication(@AuthenticationPrincipal User user, @RequestBody PublicationEntityDTO dto){
        PublicationEntity publication = sellerService.createPublication(user, dto);
        return publicationMapper.toPublicationEntityDTO(publication);
    }
}
