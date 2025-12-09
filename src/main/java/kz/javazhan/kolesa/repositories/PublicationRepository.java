package kz.javazhan.kolesa.repositories;


import kz.javazhan.kolesa.entities.Publication;
import kz.javazhan.kolesa.entities.Seller;
import kz.javazhan.kolesa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PublicationRepository extends JpaRepository<Publication, UUID>{
    Publication findByAuthor(Seller user);

    List<Publication> findAllByAuthor(Seller seller);
}
