package kz.javazhan.kolesa.mappers;

import kz.javazhan.kolesa.entities.DTO.PublicationDTO;
import kz.javazhan.kolesa.entities.Publication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicationMapper {
    PublicationDTO toPublicationDTO(Publication publication);

    Publication toPublication(PublicationDTO publicationDTO);
}
