package kz.javazhan.kolesa.controller;

import kz.javazhan.kolesa.entities.Publication;
import kz.javazhan.kolesa.entities.User;
import kz.javazhan.kolesa.mappers.PublicationMapper;
import kz.javazhan.kolesa.services.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



import kz.javazhan.kolesa.entities.DTO.PublicationDTO;

@RestController
@RequestMapping("/api/publications")
@RequiredArgsConstructor
class PublicationController {
    private final PublicationService publicationService;
    private final PublicationMapper publicationMapper;

    @GetMapping()
    public List<PublicationDTO> getAllPublications(){
        return publicationService.getAllPublications().stream()
                .map(publicationMapper::toPublicationDTO)
                .toList();
    }
    @GetMapping("/my")
    public List<PublicationDTO> getPublications(@AuthenticationPrincipal User user){
        return publicationService.getPublicationsByUser(user).stream()
                .map(publicationMapper::toPublicationDTO)
                .toList();
    }

    @GetMapping("/{publicationId}")
    public PublicationDTO getPublication(@PathVariable UUID publicationId){
        Publication publication = publicationService.getPublicationById(publicationId);
        return publicationMapper.toPublicationDTO(publication);
    }

    @PostMapping()
    public PublicationDTO savePublication(@AuthenticationPrincipal User user,@RequestBody Publication publication){
        return publicationMapper.toPublicationDTO(publicationService.save(publication, user));
    }

    @DeleteMapping("/{publicationId}")
    public void deletePublication(@PathVariable UUID publicationId) throws Exception {
        publicationService.deletePublication(publicationId); // todo implement delete function
        return;
    }

}
