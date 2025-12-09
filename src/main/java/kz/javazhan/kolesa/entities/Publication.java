package kz.javazhan.kolesa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "publications")
@EntityListeners(AuditingEntityListener.class)
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;

    @Column(length = 5000)
    private String content;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Seller author;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean published;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @ElementCollection
    private List<String> images;
}
