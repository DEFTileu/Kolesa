package kz.javazhan.kolesa.services;

import kz.javazhan.kolesa.entities.DTO.requests.AuthRequest;
import kz.javazhan.kolesa.entities.DTO.requests.RegisterRequest;
import kz.javazhan.kolesa.entities.DTO.responses.AuthResponse;
import kz.javazhan.kolesa.entities.User;
import kz.javazhan.kolesa.entities.enums.UserRole;
import kz.javazhan.kolesa.mappers.UserMapper;
import kz.javazhan.kolesa.repositories.UserRepository;
import kz.javazhan.kolesa.utils.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;


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
                .refreshToken(refreshTokenStr)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        User user = userService.findByUsername(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email"+ request.getEmail()));


        String accessToken = jwtUtils.generateToken(request.getEmail());
        String refreshToken = jwtUtils.generateRefreshToken(request.getEmail());

        return AuthResponse.builder()
                .user(userMapper.toUserDTO(user))
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

    public AuthResponse refresh(String refreshToken) {
        String email = jwtUtils.extractEmail(refreshToken);
        log.info(email);
        User user = userService.findByUsername(email).orElseThrow(
                () ->  new UsernameNotFoundException("User not found by email"+ email));
        if(!jwtUtils.isTokenValid(refreshToken,email)){
            throw new RuntimeException("Invalid Token is expired");
        }

        String newAccessToken = jwtUtils.generateToken(email);
        String newRefreshToken = jwtUtils.generateRefreshToken(email);

        return AuthResponse.builder()
                .user(userMapper.toUserDTO(user))
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }


    public User getCurrentUser() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()){
            throw new AuthenticationException("User not authenticated");
        }

        String email = authentication.getName();
        return userRepository.findUserByUsername(email)
                .orElseThrow(() -> new RuntimeException("User not found by email "+ email));

    }

    // пустой
    //ali pustoi ma


}
