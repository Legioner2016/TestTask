package com.task.service;

import com.task.dto.CompanyDto;
import com.task.dto.UserDto;
import com.task.dto.UserJobInfoDto;
import com.task.model.Gender;
import com.task.repository.UserJobInfoRepository;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserJobInfoServiceTest {

    @Autowired
    UserJobInfoRepository repository;

    @Autowired
    UserJobInfoService service;

    @Test
    void testAddUserJobInfo() {
        var dto = prepareUserJobInfoDto();
        service.addUserJobInfo(dto);
        assertThat(repository.findById(dto.getId())).isPresent();
    }

    @Test
    void testUpdateUserJobInfo() {
        var dto = prepareUserJobInfoDto();
        service.addUserJobInfo(dto);
        dto.setIsActivity(false);
        service.updateUserJobInfo(dto);
        assertThat(repository.findById(dto.getId())).isPresent()
                .get()
                .hasFieldOrPropertyWithValue("isActivity", false);
    }

    @Test
    void testFindUserJobInfo() {
        var first = prepareUserJobInfoDto();
        var second = prepareUserJobInfoDto();
        service.addUserJobInfo(first);
        service.addUserJobInfo(second);

        var filter1 = prepareUserJobInfoDto();
        filter1.getUser().setId(first.getUser().getId());
        var filter2 = prepareUserJobInfoDto();
        filter2.setUser(null);
        filter2.getCompany().setId(second.getCompany().getId());
        var filterEmpty = prepareUserJobInfoDto();
        filterEmpty.setUser(null);
        filterEmpty.setCompany(null);

        RecursiveComparisonConfiguration comparisonConfig = new RecursiveComparisonConfiguration();
        comparisonConfig.ignoreFields("user.birthday");

        assertThat(service.find(filter1))
                .hasSize(1)
                .element(0)
                .usingRecursiveComparison(comparisonConfig)
                .isEqualTo(first);
        assertThat(service.find(filter2))
                .hasSize(1)
                .element(0)
                .usingRecursiveComparison(comparisonConfig)
                .isEqualTo(second);
        assertThat(service.find(filterEmpty)).isEmpty();
    }

    private UserJobInfoDto prepareUserJobInfoDto() {
        var jobInfoId = UUID.randomUUID();
        var userId = UUID.randomUUID();
        var companyId = UUID.randomUUID();
        var userDto = new UserDto();
        userDto.setId(userId);
        userDto.setAge(10);
        userDto.setBirthday(Instant.now());
        userDto.setFamilyName(userId.toString());
        userDto.setFirstName(userId.toString());
        userDto.setMiddleName(userId.toString());
        userDto.setGender(Gender.FEMALE);
        userDto.setIsBlocked(false);
        var companyDto = new CompanyDto();
        companyDto.setId(companyId);
        companyDto.setCompanyName(companyId.toString());
        companyDto.setIsActivity(true);
        var dto = new UserJobInfoDto();
        dto.setId(UUID.randomUUID());
        dto.setIsActivity(true);
        dto.setDescription(jobInfoId.toString());
        dto.setUser(userDto);
        dto.setCompany(companyDto);
        return dto;
    }
}
