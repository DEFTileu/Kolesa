package kz.javazhan.kolesa.repositories;


import kz.javazhan.kolesa.entities.PublicationEntity;
import kz.javazhan.kolesa.entities.Seller;
import kz.javazhan.kolesa.entities.User;
import kz.javazhan.kolesa.state.PublicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PublicationEntityRepository extends JpaRepository<PublicationEntity, UUID>{
    PublicationEntity findByAuthor(Seller user);

    List<PublicationEntity> findAllByAuthor(Seller seller);

    List<PublicationEntity> findAllByStatus(PublicationStatus status);
}
