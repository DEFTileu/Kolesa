package kz.javazhan.kolesa.entities;

import jakarta.persistence.*;
import kz.javazhan.kolesa.state.*;
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
@EntityListeners(AuditingEntityListener.class)
public class PublicationEntity implements PublicationI {

    public PublicationEntity(UUID id, String title, String description, String content,
                             LocalDateTime createdAt, Seller author, List<String> images,
                             PublicationStatus status, PublicationState state) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.images = images;
        this.status = status != null ? status : PublicationStatus.DRAFT;
        this.state = state;
        if (this.state == null) {
            initializeState();
        }
    }

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PublicationStatus status = PublicationStatus.DRAFT;

    @Transient
    private PublicationState state;

    @PostLoad
    @PostPersist
    private void initializeState() {
        if (this.state == null) {
            this.state = switch (this.status) {
                case DRAFT -> new DraftState();
                case UNDER_REVIEW -> new UnderReviewState();
                case PUBLISHED -> new PublishedState();
                case ARCHIVED -> new ArchivedState();
            };
        }
    }

    public boolean isPublished() {
        return this.status == PublicationStatus.PUBLISHED;
    }

    public void publish() {
        if (state == null) initializeState();
        state.publish(this);
    }

    public void sendToReview() {
        if (state == null) initializeState();
        state.sendToReview(this);
    }

    public void archive() {
        if (state == null) initializeState();
        state.archive(this);
    }

    public void reject() {
        if (state == null) initializeState();
        state.reject(this);
    }

    public String getStatusMessage() {
        if (state == null) initializeState();
        return state.getStatusMessage();
    }

    public void setState(PublicationState state) {
        this.state = state;
    }

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
        private PublicationStatus status = PublicationStatus.DRAFT;

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
        public PublicationBuilder status(PublicationStatus status) {
            this.status = status;
            return PublicationBuilder.this;
        }

        public PublicationEntity build() {
            PublicationEntity entity = new PublicationEntity(id, title, description, content, createdAt, author, images, status, null);
            entity.initializeState();
            return entity;
        }
    }


}
