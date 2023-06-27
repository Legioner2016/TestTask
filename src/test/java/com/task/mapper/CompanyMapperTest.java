package com.task.mapper;

import com.task.dto.CompanyDto;
import com.task.model.Company;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CompanyMapperTest {

    @InjectMocks
    CompanyMapper companyMapper;

    @Test
    void toDto() {
        var model = Company.builder()
                .id(UUID.randomUUID())
                .isActivity(true)
                .companyName("company")
                .description("test")
                .build();
        assertThat(companyMapper.toDto(model))
                .hasFieldOrPropertyWithValue("isActivity", true)
                .hasFieldOrPropertyWithValue("description", "test")
                .hasFieldOrPropertyWithValue("companyName", "company")
                .hasFieldOrProperty("id").isNotNull();
    }

    @Test
    void toModel() {
        var dto = new CompanyDto();
        dto.setId(UUID.randomUUID());
        dto.setIsActivity(true);
        dto.setCompanyName("company");
        dto.setDescription("test");
        assertThat(companyMapper.toModel(dto))
                .hasFieldOrPropertyWithValue("isActivity", true)
                .hasFieldOrPropertyWithValue("description", "test")
                .hasFieldOrPropertyWithValue("companyName", "company")
                .hasFieldOrProperty("id").isNotNull();
    }
}