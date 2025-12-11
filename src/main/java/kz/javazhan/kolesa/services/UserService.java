package kz.javazhan.kolesa.services;

import kz.javazhan.kolesa.entities.DTO.UserDTO;
import kz.javazhan.kolesa.entities.User;
import kz.javazhan.kolesa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findUserByUsername(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found with username: " + email));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User editProfile(User user) throws Exception {
        User currUser = getCurrentUser();
        currUser.setFirstName(user.getFirstName());
        currUser.setLastName(user.getLastName());
        currUser.setAvatarUrl(user.getAvatarUrl());
        return userRepository.save(currUser);
    }

    public User getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                throw new AuthenticationException("User not authenticated");
            }

            String email = authentication.getName();
            return userRepository.findUserByUsername(email)
                    .orElseThrow(() -> new RuntimeException("User not found by email " + email));
        }catch (Exception e){
            throw new RuntimeException("Error retrieving current user: " + e.getMessage());
        }
    }
}
