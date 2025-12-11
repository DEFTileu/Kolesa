package kz.javazhan.kolesa.iterators;

import kz.javazhan.kolesa.entities.PublicationEntity;

import java.util.List;
import java.util.NoSuchElementException;

public class AllPublicationsIterator implements PublicationIterator {
    private final List<PublicationEntity> publications;
    private int currentPosition = 0;

    public AllPublicationsIterator(List<PublicationEntity> publications) {
        this.publications = publications;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < publications.size();
    }

    @Override
    public PublicationEntity next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more publications available");
        }
        return publications.get(currentPosition++);
    }
}
