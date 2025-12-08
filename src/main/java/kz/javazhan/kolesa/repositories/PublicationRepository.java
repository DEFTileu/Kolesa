package kz.javazhan.kolesa.repositories;


import kz.javazhan.kolesa.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface PublicationRepository extends JpaRepository<Publication, UUID>{
}
