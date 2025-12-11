package kz.javazhan.kolesa.iterators;

import kz.javazhan.kolesa.entities.PublicationEntity;

public interface PublicationIterator {
    boolean hasNext();
    PublicationEntity next();
}
