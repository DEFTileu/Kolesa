package kz.javazhan.kolesa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "publications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PublicationEntity implements PublicationI {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Seller author;

    @ElementCollection
    private List<String> images;

    private boolean published;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public static PublicationBuilder builder() {
        return new PublicationBuilder();
    }


    public static class PublicationBuilder{
        private UUID id;
        private String title;
        private String description;
        private String content;
        private LocalDateTime createdAt;
        private Seller author;
        private List<String> images;
        private boolean published;


        PublicationBuilder(){}

        public static PublicationBuilder builder() {
            return new PublicationBuilder();
        }

        public PublicationBuilder id(UUID id) {
            this.id = id;
            return PublicationBuilder.this;
        }
        public PublicationBuilder title(String title) {
            this.title = title;
            return PublicationBuilder.this;
        }
        public PublicationBuilder description(String description) {
            this.description = description;
            return PublicationBuilder.this;
        }
        public PublicationBuilder content(String content) {
            this.content = content;
            return PublicationBuilder.this;
        }
        public PublicationBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return PublicationBuilder.this;
        }
        public PublicationBuilder author(Seller author) {
            this.author = author;
            return PublicationBuilder.this;
        }
        public PublicationBuilder images(List<String> images) {
            this.images = images;
            return PublicationBuilder.this;
        }
        public PublicationBuilder published(boolean published) {
            this.published = published;
            return PublicationBuilder.this;
        }
        public PublicationEntity build() {
            return new PublicationEntity(id, title, description, content, createdAt, author, images, published);
        }
    }


}
