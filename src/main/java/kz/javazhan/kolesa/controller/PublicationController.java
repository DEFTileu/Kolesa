package kz.javazhan.kolesa.controller;

import kz.javazhan.kolesa.entities.DTO.PublicationEntityDTO;
import kz.javazhan.kolesa.entities.Publication;
import kz.javazhan.kolesa.entities.PublicationEntity;
import kz.javazhan.kolesa.mappers.PublicationEntityMapper;
import kz.javazhan.kolesa.services.PublicationEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



import kz.javazhan.kolesa.entities.DTO.PublicationDTO;

@RestController
@RequestMapping("/api/publications")
@RequiredArgsConstructor
class PublicationController {
    private final PublicationEntityService publicationService;
    private final PublicationEntityMapper publicationMapper;

    @GetMapping()
    public List<PublicationEntityDTO> getAllPublications(){
        return publicationService.getAllPublications().stream()
                .map(publicationMapper::toPublicationEntityDTO)
                .toList();
    }

    @GetMapping("/{publicationId}")
    public PublicationEntityDTO getPublication(@PathVariable UUID publicationId){
        PublicationEntity publication = publicationService.getPublicationById(publicationId);
        return publicationMapper.toPublicationEntityDTO(publication);
    }

}
