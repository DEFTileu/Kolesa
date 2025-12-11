package kz.javazhan.kolesa.entities.DTO;

import kz.javazhan.kolesa.state.PublicationStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class PublicationEntityDTO {
    private UUID id;
    private String title;
    private String description;
    private String Content;
    private SellerDTO author;
    private List<String> images;
    private PublicationStatus status;
    private String statusMessage;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private String authorNotes;
}