package kz.javazhan.kolesa.repositories;


import kz.javazhan.kolesa.entities.Publication;
import kz.javazhan.kolesa.entities.PublicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface PublicationEntityRepository extends JpaRepository<PublicationEntity, UUID>{
}
