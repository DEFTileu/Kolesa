package kz.javazhan.kolesa.entities.DTO.responses;

import kz.javazhan.kolesa.entities.DTO.UserDTO;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AuthResponse {
    private UserDTO user;
    private String accessToken;
    private String refreshToken;
}
