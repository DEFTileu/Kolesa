package kz.javazhan.kolesa.controller;

import kz.javazhan.kolesa.entities.DTO.UserDTO;
import kz.javazhan.kolesa.entities.DTO.requests.AuthRequest;
import kz.javazhan.kolesa.entities.DTO.requests.RefreshTokenRequest;
import kz.javazhan.kolesa.entities.DTO.requests.RegisterRequest;
import kz.javazhan.kolesa.entities.DTO.responses.AuthResponse;
import kz.javazhan.kolesa.mappers.UserMapper;
import kz.javazhan.kolesa.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserMapper userMapper;


    @PostMapping("/signup")
    public AuthResponse register(@RequestBody RegisterRequest request) throws Exception {
        return authService.register(request);
    }




    @PostMapping("/signin")
    public AuthResponse login(@RequestBody AuthRequest request){
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest refreshToken){
        String refreshTokenStr = refreshToken.getRefreshToken();
        return authService.refresh(refreshTokenStr);
    }
}
