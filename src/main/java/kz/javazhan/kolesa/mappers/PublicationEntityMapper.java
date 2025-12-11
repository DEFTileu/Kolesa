package kz.javazhan.kolesa.mappers;

import kz.javazhan.kolesa.entities.DTO.PublicationEntityDTO;
import kz.javazhan.kolesa.entities.PublicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PublicationEntityMapper {
    @Mapping(source = "status", target = "status")
    @Mapping(target = "statusMessage", expression = "java(publication.getStatusMessage())")
    PublicationEntityDTO toPublicationEntityDTO(PublicationEntity publication);

    @Mapping(source = "status", target = "status")
    PublicationEntity toPublicationEntity(PublicationEntityDTO publicationDTO);
}
