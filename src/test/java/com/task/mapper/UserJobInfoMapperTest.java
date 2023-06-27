package com.task.mapper;

import com.task.dto.CompanyDto;
import com.task.dto.UserDto;
import com.task.dto.UserJobInfoDto;
import com.task.model.Company;
import com.task.model.User;
import com.task.model.UserJobInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserJobInfoMapperTest {

    @Mock
    UserMapper userMapper;
    @Mock
    CompanyMapper companyMapper;

    @InjectMocks
    UserJobInfoMapper userJobInfoMapper;

    @Test
    void toDto() {
        var model = UserJobInfo.builder()
                .id(UUID.randomUUID())
                .isActivity(true)
                .description("test")
                .user(mock(User.class))
                .company(mock(Company.class))
                .build();
        assertThat(userJobInfoMapper.toDto(model))
                .hasFieldOrPropertyWithValue("isActivity", true)
                .hasFieldOrPropertyWithValue("description", "test")
                .hasFieldOrProperty("id").isNotNull()
                .hasFieldOrProperty("user").isNotNull()
                .hasFieldOrProperty("company").isNotNull();
    }

    @Test
    void toModel() {
        var dto = new UserJobInfoDto();
        dto.setId(UUID.randomUUID());
        dto.setIsActivity(true);
        dto.setDescription("test");
        dto.setUser(mock(UserDto.class));
        dto.setCompany(mock(CompanyDto.class));
        assertThat(userJobInfoMapper.toModel(dto))
                .hasFieldOrPropertyWithValue("isActivity", true)
                .hasFieldOrPropertyWithValue("description", "test")
                .hasFieldOrProperty("id").isNotNull()
                .hasFieldOrProperty("user").isNotNull()
                .hasFieldOrProperty("company").isNotNull();
    }
}