package kz.javazhan.kolesa.entities.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Data
public class PublicationDTO {
    private UUID id;
    private String title;
    private String description;
    private String Content;
    private UserDTO author;
    private List<String> images;
    private boolean published;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
