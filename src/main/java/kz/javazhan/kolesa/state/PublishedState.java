package kz.javazhan.kolesa.state;

import kz.javazhan.kolesa.entities.PublicationEntity;

public class PublishedState implements PublicationState {

    @Override
    public PublicationStatus getStatus() {
        return PublicationStatus.PUBLISHED;
    }

    @Override
    public void publish(PublicationEntity publication) {
        throw new IllegalStateException("Публикация уже опубликована.");
    }

    @Override
    public void sendToReview(PublicationEntity publication) {
        throw new IllegalStateException("Опубликованную публикацию нельзя отправить на модерацию. Сначала архивируйте её.");
    }

    @Override
    public void archive(PublicationEntity publication) {
        publication.setStatus(PublicationStatus.ARCHIVED);
        publication.setState(new ArchivedState());
    }

    @Override
    public void reject(PublicationEntity publication) {
        throw new IllegalStateException("Нельзя отклонить опубликованную публикацию.");
    }

    @Override
    public String getStatusMessage() {
        return "Публикация опубликована и доступна для всех пользователей.";
    }
}

