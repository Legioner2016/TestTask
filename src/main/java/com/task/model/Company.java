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
@Table(name = "COMPANY")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    UUID id;
    @Column(name = "company_name")
    String companyName;
    String description;
    @CreationTimestamp
    Instant created;
    @UpdateTimestamp
    Instant updated;
    @Column(name = "is_activity")
    @Builder.Default
    Boolean isActivity = true;

}