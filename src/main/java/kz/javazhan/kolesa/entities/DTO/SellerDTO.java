package kz.javazhan.kolesa.entities.DTO;

import kz.javazhan.kolesa.entities.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SellerDTO {
    private UUID id;
    private UserDTO user;
    private LocalDateTime createdAt;
}
