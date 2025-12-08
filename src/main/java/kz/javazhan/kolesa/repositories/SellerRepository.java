package kz.javazhan.kolesa.repositories;

import kz.javazhan.kolesa.entities.Seller;
import kz.javazhan.kolesa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID> {
    Optional<Seller> findSellerByUsername(String email);

}
