package kz.javazhan.kolesa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sellers")
@EntityListeners(AuditingEntityListener.class)
@Deprecated
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @Column(name = "user", nullable = false)
    private User user;

    @CreatedDate
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<PublicationEntity> publications = new ArrayList<>();

    public PublicationEntity createPublication(String title, String description, String content, List<String> images, String authorNotes){
        PublicationEntity publication = new PublicationEntity();
        publication.setTitle(title);
        publication.setDescription(description);
        publication.setContent(content);
        publication.setAuthor(this);
        publication.setImages(images);

        publication = new AuthorNotesPublicationDecorator(publication, authorNotes);

        publications.add(publication);

        return publication;


    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Seller seller = (Seller) object;
        return Objects.equals(id, seller.id) && Objects.equals(createdAt, seller.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }
}
