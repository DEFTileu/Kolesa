package kz.javazhan.kolesa.entities;

import jakarta.persistence.*;
import kz.javazhan.kolesa.entities.enums.UserRole;
import lombok.*;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


//    Bakosh's произведение
//    @Column(name = "instance_id")
//    private static boolean instance = false;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "password")
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

// тут тоже
//    public static boolean getInstance(){
//        boolean current = instance;
//        if (instance){
//            return instance;
//        }
//        instance = !instance ? true : false;
//        return current;
//    }





    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getAvatarUrl(), user.getAvatarUrl()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && getRole() == user.getRole() && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getCreatedAt(), user.getCreatedAt()) && Objects.equals(getUpdatedAt(), user.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getAvatarUrl(), getFirstName(), getLastName(), getRole(), getPassword(), getCreatedAt(), getUpdatedAt());
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }


    public static class UserBuilder{
        private UUID id;
        private String username;
        private String avatarUrl;
        private String firstName;
        private String lastName;
        private UserRole role;
        private String password;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;


        UserBuilder(){}

        public UserBuilder id(UUID id) {
            this.id = id;
            return UserBuilder.this;
        }
        public UserBuilder username(String username) {
            this.username = username;
            return UserBuilder.this;
        }
        public UserBuilder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return UserBuilder.this;
        }
        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return UserBuilder.this;
        }
        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return UserBuilder.this;
        }
        public UserBuilder role(UserRole role) {
            this.role = role;
            return UserBuilder.this;
        }
        public UserBuilder password(String password) {
            this.password = password;
            return UserBuilder.this;
        }
        public UserBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return UserBuilder.this;
        }
        public UserBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return UserBuilder.this;
        }
        public User build() {
            return new User(id, username, avatarUrl, firstName, lastName, role, password, createdAt, updatedAt);
        }

    }


}
