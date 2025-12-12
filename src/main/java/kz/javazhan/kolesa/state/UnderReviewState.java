package kz.javazhan.kolesa.state;

import kz.javazhan.kolesa.entities.PublicationEntity;

public class UnderReviewState implements PublicationState {

    @Override
    public void publish(PublicationEntity publication) {
        publication.setStatus(PublicationStatus.PUBLISHED);
        publication.setState(new PublishedState());
    }

    @Override
    public void sendToReview(PublicationEntity publication) {
        throw new IllegalStateException("Публикация уже находится на модерации.");
    }

    @Override
    public void archive(PublicationEntity publication) {
        publication.setStatus(PublicationStatus.ARCHIVED);
        publication.setState(new ArchivedState());
    }

    @Override
    public void reject(PublicationEntity publication) {
        publication.setStatus(PublicationStatus.DRAFT);
        publication.setState(new DraftState());
    }

    @Override
    public String getStatusMessage() {
        return "Публикация находится на модерации. Ожидает одобрения или отклонения.";
    }
}

