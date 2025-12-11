package kz.javazhan.kolesa.entities.DTO.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private boolean success;

    private String message;

    public static ErrorResponseBuilder builder(){
        return new ErrorResponseBuilder();
    }
    public static class ErrorResponseBuilder{
        private boolean success;
        private String message;

        public ErrorResponseBuilder success(boolean success){
            this.success = success;
            return this;
        }
        public ErrorResponseBuilder message(String message){
            this.message = message;
            return this;
        }
        public ErrorResponse build(){
            return new ErrorResponse(success, message);
        }
    }

}
