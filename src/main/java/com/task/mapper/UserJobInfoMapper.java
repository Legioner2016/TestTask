package com.task.mapper;

import com.task.dto.UserJobInfoDto;
import com.task.model.UserJobInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserJobInfoMapper {
    final UserMapper userMapper;
    final CompanyMapper companyMapper;

    public UserJobInfoDto toDto(UserJobInfo model) {
        var dto = new UserJobInfoDto();
        dto.setId(model.getId());
        dto.setDescription(model.getDescription());
        dto.setIsActivity(model.getIsActivity());
        dto.setUser(model.getUser() != null ? userMapper.toDto(model.getUser()) : null);
        dto.setCompany(model.getCompany() != null ? companyMapper.toDto(model.getCompany()) : null);
        return dto;
    }

    public UserJobInfo toModel(UserJobInfoDto dto) {
        return UserJobInfo.builder()
                .id(dto.getId() == null ? UUID.randomUUID() : dto.getId())
                .description(dto.getDescription())
                .company(companyMapper.toModel(dto.getCompany()))
                .user(userMapper.toModel(dto.getUser()))
                .isActivity(dto.getIsActivity())
                .build();
    }
}

