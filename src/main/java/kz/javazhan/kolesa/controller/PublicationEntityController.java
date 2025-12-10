package kz.javazhan.kolesa.controller;

import kz.javazhan.kolesa.entities.DTO.PublicationEntityDTO;
import kz.javazhan.kolesa.entities.PublicationEntity;
import kz.javazhan.kolesa.entities.User;
import kz.javazhan.kolesa.mappers.PublicationEntityMapper;
import kz.javazhan.kolesa.services.PublicationEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/publications")
@RequiredArgsConstructor
class PublicationEntityController {
    private final PublicationEntityService publicationService;
    private final PublicationEntityMapper publicationMapper;

    @GetMapping()
    public List<PublicationEntityDTO> getAllPublications(){
        return publicationService.getAllPublications().stream()
                .map(publicationMapper::toPublicationEntityDTO)
                .toList();
    }
    @GetMapping("/my")
    public List<PublicationEntityDTO> getPublications(@AuthenticationPrincipal User user){
        return publicationService.getPublicationsByUser(user).stream()
                .map(publicationMapper::toPublicationEntityDTO)
                .toList();
    }

    @GetMapping("/{publicationId}")
    public PublicationEntityDTO getPublication(@PathVariable UUID publicationId){
        PublicationEntity publication = publicationService.getPublicationById(publicationId);
        return publicationMapper.toPublicationEntityDTO(publication);
    }

    @PostMapping()
    public PublicationEntityDTO savePublication(@AuthenticationPrincipal User user,@RequestBody PublicationEntity publication){
        return publicationMapper.toPublicationEntityDTO(publicationService.save(publication, user));
    }

    @DeleteMapping("/{publicationId}")
    public void deletePublication(@PathVariable UUID publicationId) throws Exception {
        publicationService.deletePublication(publicationId); // todo implement delete function
        return;
    }

}
