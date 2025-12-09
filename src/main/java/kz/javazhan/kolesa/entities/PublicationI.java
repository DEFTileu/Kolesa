package kz.javazhan.kolesa.entities;


import java.time.LocalDateTime;

public interface PublicationI {
    String getTitle();
    String getDescription();
    String getContent();
    LocalDateTime getCreatedAt();
}
