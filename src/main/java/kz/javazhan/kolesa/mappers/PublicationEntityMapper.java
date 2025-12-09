package kz.javazhan.kolesa.mappers;

import kz.javazhan.kolesa.entities.DTO.PublicationEntityDTO;
import kz.javazhan.kolesa.entities.PublicationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicationEntityMapper {
    PublicationEntityDTO toPublicationEntityDTO(PublicationEntity publication);

    PublicationEntity toPublicationEntity(PublicationEntityDTO publicationDTO);
}
