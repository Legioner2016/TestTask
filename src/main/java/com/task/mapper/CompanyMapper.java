package com.task.mapper;

import com.task.dto.CompanyDto;
import com.task.model.Company;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CompanyMapper {

    public CompanyDto toDto(Company model) {
        var dto = new CompanyDto();
        dto.setId(model.getId());
        dto.setDescription(model.getDescription());
        dto.setCompanyName(model.getCompanyName());
        dto.setIsActivity(model.getIsActivity());
        return dto;
    }

    public Company toModel(CompanyDto dto) {
        return Company.builder()
                .id(dto.getId() == null ? UUID.randomUUID() : dto.getId())
                .companyName(dto.getCompanyName())
                .description(dto.getDescription())
                .isActivity(dto.getIsActivity())
                .build();
    }

}