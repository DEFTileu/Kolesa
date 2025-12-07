package kz.javazhan.kolesa.mappers;

import kz.javazhan.kolesa.entities.DTO.UserDTO;
import kz.javazhan.kolesa.entities.DTO.requests.RegisterRequest;
import org.mapstruct.Mapper;
import kz.javazhan.kolesa.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterRequest request);
    UserDTO toUserDTO(User user);
}
