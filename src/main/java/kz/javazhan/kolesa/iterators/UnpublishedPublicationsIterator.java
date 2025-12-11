package kz.javazhan.kolesa.iterators;

import kz.javazhan.kolesa.entities.PublicationEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UnpublishedPublicationsIterator implements  PublicationIterator {
    private final List<PublicationEntity> unpublishedPublications;
    private int currentPosition = 0;

    public UnpublishedPublicationsIterator(List<PublicationEntity> publications) {
        this.unpublishedPublications = new ArrayList<>();
        for (PublicationEntity publication : publications) {
            if (!publication.isPublished()) {
                this.unpublishedPublications.add(publication);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return currentPosition < unpublishedPublications.size();
    }

    @Override
    public PublicationEntity next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more unpublished publications available");
        }
        return unpublishedPublications.get(currentPosition++);
    }
}
