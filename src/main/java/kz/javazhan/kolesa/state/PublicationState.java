package kz.javazhan.kolesa.state;

import kz.javazhan.kolesa.entities.PublicationEntity;

public interface PublicationState {
    PublicationStatus getStatus();

    void publish(PublicationEntity publication);
    void sendToReview(PublicationEntity publication);
    void archive(PublicationEntity publication);
    void reject(PublicationEntity publication);

    String getStatusMessage();
}
