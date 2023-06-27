package com.task.repository.specification;

import com.task.model.Company;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class CompanyJpaSpecification {

    public static Specification<Company> hasCompanyName(String name) {
        return name == null
                ? (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.<String>get("companyName"))
                : (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<String>get("companyName"), name);
    }

    public static Specification<Company> isActivity(Boolean isActivity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<Boolean>get("isActivity"), Boolean.TRUE.equals(isActivity));
    }

    public static Specification<Company> hasId(UUID id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<UUID>get("id"), id);
    }
}
