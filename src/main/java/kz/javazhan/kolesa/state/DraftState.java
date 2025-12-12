package kz.javazhan.kolesa.state;

import kz.javazhan.kolesa.entities.PublicationEntity;

public class DraftState implements PublicationState {

    @Override
    public void publish(PublicationEntity publication) {
        throw new IllegalStateException("Нельзя опубликовать черновик напрямую. Сначала отправьте на модерацию.");
    }

    @Override
    public void sendToReview(PublicationEntity publication) {
        publication.setStatus(PublicationStatus.UNDER_REVIEW);
        publication.setState(new UnderReviewState());
    }

    @Override
    public void archive(PublicationEntity publication) {
        publication.setStatus(PublicationStatus.ARCHIVED);
        publication.setState(new ArchivedState());
    }

    @Override
    public void reject(PublicationEntity publication) {
        throw new IllegalStateException("Нельзя отклонить черновик. Он уже находится в статусе черновика.");
    }

    @Override
    public String getStatusMessage() {
        return "Публикация находится в статусе черновика. Отправьте на модерацию для публикации.";
    }
}

