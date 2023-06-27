package com.task.repository.specification;

import com.task.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class UserJpaSpecification {

    public static Specification<User> hasFirstName(String name) {
        return name == null
        ? (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.<String>get("firstName"))
        : (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<String>get("firstName"), name);
    }

    public static Specification<User> hasFamilyName(String name) {
        return name == null
                ? (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.<String>get("familyName"))
                : (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<String>get("familyName"), name);
    }

    public static Specification<User> hasMiddleName(String name) {
        return name == null
                ? (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.<String>get("middleName"))
                : (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<String>get("middleName"), name);
    }

    public static Specification<User> isBlocked(Boolean isBlocked) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<Boolean>get("isBlocked"), Boolean.TRUE.equals(isBlocked));
    }

    public static Specification<User> hasId(UUID id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<UUID>get("id"), id);
    }


}
