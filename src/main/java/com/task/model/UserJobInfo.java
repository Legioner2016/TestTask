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
@Table(name = "USER_JOB_INFO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserJobInfo {
    @Id
    UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_company")
    Company company;
    String description;
    @CreationTimestamp
    Instant created;
    @UpdateTimestamp
    Instant updated;
    @Column(name = "is_activity")
    @Builder.Default
    Boolean isActivity = true;

}

