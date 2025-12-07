package kz.javazhan.kolesa.entities.DTO.requests;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
}
