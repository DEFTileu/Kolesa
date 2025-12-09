package kz.javazhan.kolesa.entities.DTO.requests;

import kz.javazhan.kolesa.entities.Publication;
import kz.javazhan.kolesa.entities.Seller;
import lombok.Data;

import java.util.List;

@Data
public class CreatePublicationDTORequest {
    private Publication publication;
    private Seller author;
    private List<String> images;
}
