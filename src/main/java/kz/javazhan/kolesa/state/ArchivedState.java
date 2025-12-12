package kz.javazhan.kolesa.state;

import kz.javazhan.kolesa.entities.PublicationEntity;

public class ArchivedState implements PublicationState {
    @Override
    public void publish(PublicationEntity publication) {
        throw new IllegalStateException("Нельзя опубликовать архивированную публикацию. Сначала отправьте на модерацию.");
    }

    @Override
    public void sendToReview(PublicationEntity publication) {
        publication.setStatus(PublicationStatus.UNDER_REVIEW);
        publication.setState(new UnderReviewState());
    }

    @Override
    public void archive(PublicationEntity publication) {
        throw new IllegalStateException("Публикация уже архивирована.");
    }

    @Override
    public void reject(PublicationEntity publication) {
        throw new IllegalStateException("Нельзя отклонить архивированную публикацию.");
    }

    @Override
    public String getStatusMessage() {
        return "Публикация архивирована и недоступна для просмотра.";
    }
}

