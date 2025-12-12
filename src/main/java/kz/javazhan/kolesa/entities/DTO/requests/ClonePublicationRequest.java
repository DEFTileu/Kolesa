package kz.javazhan.kolesa.entities.DTO.requests;


import lombok.Data;

@Data
public class ClonePublicationRequest {
    private boolean copyImages;
    private boolean clearDescription;
    private String newTitle;
}
