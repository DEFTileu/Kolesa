package kz.javazhan.kolesa.controller;

import kz.javazhan.kolesa.entities.DTO.requests.RegisterRequest;
import kz.javazhan.kolesa.entities.DTO.responses.AuthResponse;
import kz.javazhan.kolesa.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;



    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) throws Exception {
        return authService.register(request);
    }
}
