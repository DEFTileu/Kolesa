package kz.javazhan.kolesa.services;

import kz.javazhan.kolesa.entities.DTO.requests.RegisterRequest;
import kz.javazhan.kolesa.entities.DTO.responses.AuthResponse;
import kz.javazhan.kolesa.entities.User;
import kz.javazhan.kolesa.entities.enums.UserRole;
import kz.javazhan.kolesa.mappers.UserMapper;
import kz.javazhan.kolesa.utils.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;

    public AuthResponse register(RegisterRequest request) throws Exception {
        Optional<User> existingUserOpt = userService.findByUsername(request.getUsername());

        if (existingUserOpt.isPresent()){
            throw new RuntimeException("User with this email "+ request.getUsername() + "already exists. Please Log in");
//            User user = existingUserOpt.get();
//
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userService.save(user);
        }
        User user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.ROLE_USER)
                .build();


        user = userService.save(user);

        String accessToken = jwtUtils.generateToken(user.getUsername());
        String refreshTokenStr = jwtUtils.generateRefreshToken(user.getUsername());


        return AuthResponse.builder()
                .user(userMapper.toUserDTO(user))
                .accessToken(accessToken)
                .RefreshToken(refreshTokenStr)
                .build();
    }
}
