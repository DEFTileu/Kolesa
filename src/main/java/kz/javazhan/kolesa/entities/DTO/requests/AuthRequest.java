package kz.javazhan.kolesa.entities.DTO.requests;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
