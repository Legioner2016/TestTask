package com.task.repository;

import com.task.model.UserJobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserJobInfoRepository extends JpaRepository<UserJobInfo, UUID>, JpaSpecificationExecutor<UserJobInfo> {
}

