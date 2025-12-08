package kz.javazhan.kolesa.entities.DTO;

import kz.javazhan.kolesa.entities.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String username;
    private String avatarUrl;
    private String firstName;
    private String lastName;
    private UserRole role;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
