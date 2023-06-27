package com.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    UUID id;
    @Column(name = "family_name")
    String familyName;
    @Column(name = "middle_name")
    String middleName;
    @Column(name = "first_name")
    String firstName;
    @Temporal(TemporalType.TIMESTAMP)
    Instant birthday;
    @Enumerated(EnumType.STRING)
    Gender gender;
    Integer age;
    String description;
    @CreationTimestamp
    Instant created;
    @UpdateTimestamp
    Instant updated;
    @Column(name = "is_blocked")
    @Builder.Default
    Boolean isBlocked = false;

}