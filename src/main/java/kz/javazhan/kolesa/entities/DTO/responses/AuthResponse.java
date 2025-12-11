package kz.javazhan.kolesa.entities.DTO.responses;

import kz.javazhan.kolesa.entities.DTO.UserDTO;
import lombok.Data;


@Data
public class AuthResponse {
    private UserDTO user;
    private String accessToken;
    private String refreshToken;


    public static AuthResponseBuilder builder(){
        return new AuthResponseBuilder();
    }

    public static class AuthResponseBuilder{
        private UserDTO user;
        private String accessToken;
        private String refreshToken;


        public AuthResponseBuilder accessToken(String accessToken){
            this.accessToken = accessToken;
            return this;
        }
        public AuthResponseBuilder refreshToken(String refreshToken){
            this.refreshToken = refreshToken;
            return this;
        }
        public AuthResponseBuilder user(UserDTO user){
            this.user = user;
            return this;
        }
        public AuthResponse build(){
            AuthResponse response = new AuthResponse();
            response.setUser(this.user);
            response.setAccessToken(this.accessToken);
            response.setRefreshToken(this.refreshToken);
            return response;
        }
    }
}
