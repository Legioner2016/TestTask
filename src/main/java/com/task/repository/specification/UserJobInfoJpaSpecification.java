package com.task.repository.specification;

import com.task.model.Company;
import com.task.model.User;
import com.task.model.UserJobInfo;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class UserJobInfoJpaSpecification {

    public static Specification<UserJobInfo> hasUserWithId(UUID userId) {
        return (root, query, criteriaBuilder) -> {
            Join<User, UserJobInfo> jobInfoUser = root.join("user");
            return criteriaBuilder.equal(jobInfoUser.get("id"), userId);
        };
    }

    public static Specification<UserJobInfo> hasCompanyWithId(UUID companyId) {
        return (root, query, criteriaBuilder) -> {
            Join<Company, UserJobInfo> jobInfoCompany = root.join("company");
            return criteriaBuilder.equal(jobInfoCompany.get("id"), companyId);
        };
    }

    public static Specification<UserJobInfo> isActivity(Boolean isActivity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<Boolean>get("isActivity"), Boolean.TRUE.equals(isActivity));
    }
}
