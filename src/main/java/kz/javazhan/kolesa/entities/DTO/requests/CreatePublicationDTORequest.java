package kz.javazhan.kolesa.entities.DTO.requests;

import kz.javazhan.kolesa.entities.PublicationEntity;
import kz.javazhan.kolesa.entities.Seller;
import lombok.Data;

import java.util.List;

@Data
public class CreatePublicationDTORequest {
    private PublicationEntity publication;
    private Seller author;
    private List<String> images;
}
