package kz.javazhan.kolesa.entities;

import jakarta.persistence.*;
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
@Table(name = "sellers")
@EntityListeners(AuditingEntityListener.class)
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @CreatedDate
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<PublicationEntity> publications = new ArrayList<>();
    //decorator pattern
    public PublicationI createPublication(String title, String description, String content, List<String> images, String authorNotes){
        PublicationI publication = PublicationEntity.builder()
                .title(title)
                .description(description)
                .content(content)
                .images(images)
                .author(this)
                .createdAt(LocalDateTime.now())
                .build();



        publication = new AuthorNotesPublicationDecorator(publication, authorNotes);

        publications.add((PublicationEntity) publication);

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


    public static SellerBuilder builder() {
        return new SellerBuilder();
    }

    public static class SellerBuilder {
        private UUID id;
        private User user;
        private LocalDateTime createdAt;
        private List<PublicationEntity> publications;
        public SellerBuilder id(UUID id) {
            this.id = id;
            return this;
        }
        public SellerBuilder user(User user) {
            this.user = user;
            return this;
        }
        public SellerBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public SellerBuilder publications(List<PublicationEntity> publications) {
            this.publications = publications;
            return this;
        }
        public Seller build() {
            return new Seller(id, user, createdAt, publications);
        }
    }


}
