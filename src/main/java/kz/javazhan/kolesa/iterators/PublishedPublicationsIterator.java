package kz.javazhan.kolesa.iterators;

import kz.javazhan.kolesa.entities.PublicationEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PublishedPublicationsIterator implements PublicationIterator{
    private final List<PublicationEntity> publishedPublications;
    private int currentPosition = 0;

    public PublishedPublicationsIterator(List<PublicationEntity> publications) {
        this.publishedPublications = new ArrayList<>();
        for (PublicationEntity publication : publications) {
            if (publication.isPublished()) {
                this.publishedPublications.add(publication);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return currentPosition < publishedPublications.size();
    }

    @Override
    public PublicationEntity next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more published publications available");
        }
        return publishedPublications.get(currentPosition++);
    }
}
