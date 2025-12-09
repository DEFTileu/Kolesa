package kz.javazhan.kolesa.controller;

import kz.javazhan.kolesa.entities.DTO.UserDTO;
import kz.javazhan.kolesa.entities.DTO.responses.AuthResponse;
import kz.javazhan.kolesa.entities.Seller;
import kz.javazhan.kolesa.entities.User;
import kz.javazhan.kolesa.mappers.UserMapper;
import kz.javazhan.kolesa.services.AuthService;
import kz.javazhan.kolesa.services.SellerService;
import kz.javazhan.kolesa.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthService authService;
    private final SellerService sellerService;

    @GetMapping()
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers().stream()
                .map(userMapper::toUserDTO)
                .toList();
    }

    @GetMapping("/profile")
    public UserDTO profile() throws Exception {
        return userMapper.toUserDTO(authService.getCurrentUser());
    }

    @PutMapping("/profile")
    public UserDTO profile(@RequestBody User user) throws Exception {
        return userMapper.toUserDTO(userService.editProfile(user));
    }


    @PostMapping("/to-sell")
    public boolean request2sell() throws Exception {
        User user = userService.getCurrentUser();
        Seller seller =  sellerService.createSeller(user);
        return true;
    }

}

