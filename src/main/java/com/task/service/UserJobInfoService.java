package com.task.service;

import com.task.dto.UserJobInfoDto;
import com.task.exception.AlreadyExists;
import com.task.exception.NotFound;
import com.task.mapper.UserJobInfoMapper;
import com.task.model.Company;
import com.task.model.User;
import com.task.repository.CompanyRepository;
import com.task.repository.UserJobInfoRepository;
import com.task.repository.UserRepository;
import com.task.repository.specification.UserJobInfoJpaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.task.repository.specification.UserJpaSpecification.*;
import static com.task.repository.specification.CompanyJpaSpecification.*;
import static com.task.repository.specification.UserJobInfoJpaSpecification.*;

/**
 * Здесь я вижу противоречие требований:
 * - генерировать идентификаторы
 * и
 * - при создании: Проверять по id
 *
 * поэтому - проверять буду по name
 */
@Service
@RequiredArgsConstructor
public class UserJobInfoService {

    final UserRepository userRepository;
    final CompanyRepository companyRepository;
    final UserJobInfoRepository userJobInfoRepository;
    final UserJobInfoMapper userJobInfoMapper;

    @Transactional(readOnly = false)
    public void addUserJobInfo(UserJobInfoDto dto) throws AlreadyExists {
        var userJobInfo = userJobInfoMapper.toModel(dto);
        var user = userJobInfo.getUser();
        var company = userJobInfo.getCompany();
        Specification<User> userSpecification = hasFirstName(user.getFirstName()).and(hasFamilyName(user.getFamilyName()))
                .and(hasMiddleName(user.getMiddleName()));
        List<User> users = userRepository.findAll(userSpecification);
        if (!users.isEmpty()) {
            throw new AlreadyExists();
        } else {
            userRepository.save(user);
        }
        Specification<Company> companySpecification = hasCompanyName(company.getCompanyName());
        List<Company> companies = companyRepository.findAll(companySpecification);
        if (!users.isEmpty()) {
            throw new AlreadyExists();
        } else {
            companyRepository.save(company);
        }
        userJobInfoRepository.save(userJobInfo);
    }

    @Transactional(readOnly = false)
    public UserJobInfoDto updateUserJobInfo(UserJobInfoDto dto) throws NotFound {
        var userJobInfo = userJobInfoMapper.toModel(dto);
        var existingJobInfo = userJobInfoRepository.findById(userJobInfo.getId())
                .orElseThrow(() -> new NotFound());
        existingJobInfo.setUser(updateUser(userJobInfo.getUser()));
        existingJobInfo.setCompany(updateCompany(userJobInfo.getCompany()));
        existingJobInfo.setDescription(userJobInfo.getDescription());
        existingJobInfo.setIsActivity(userJobInfo.getIsActivity());
        userJobInfoRepository.save(existingJobInfo);
        return userJobInfoMapper.toDto(existingJobInfo);
    }

    @Transactional(readOnly = true)
    public List<UserJobInfoDto> find(UserJobInfoDto dto) {
        if (dto.getUser() != null && dto.getUser().getId() != null) {
            return userJobInfoRepository.findAll(hasUserWithId(dto.getUser().getId()).and(UserJobInfoJpaSpecification.isActivity(true)))
                    .stream().map(userJobInfoMapper::toDto).toList();
        }
        if (dto.getCompany() != null && dto.getCompany().getId() != null) {
            return userJobInfoRepository.findAll(hasCompanyWithId(dto.getCompany().getId()).and(UserJobInfoJpaSpecification.isActivity(true)))
                    .stream().map(userJobInfoMapper::toDto).toList();
        }
        return List.of();
    }

    private User updateUser(User newUser) {
        return userRepository.findById(newUser.getId())
                .map(existing -> {
                    existing.setAge(newUser.getAge());
                    existing.setDescription(newUser.getDescription());
                    existing.setBirthday(newUser.getBirthday());
                    existing.setGender(newUser.getGender());
                    existing.setIsBlocked(newUser.getIsBlocked());
                    existing.setMiddleName(newUser.getMiddleName());
                    existing.setFamilyName(newUser.getFamilyName());
                    existing.setFirstName(newUser.getFirstName());
                    return userRepository.save(existing);
                })
                .orElse(userRepository.save(newUser));
    }

    private Company updateCompany(Company newCompany) {
        return companyRepository.findById(newCompany.getId())
                .map(existing -> {
                    existing.setDescription(newCompany.getDescription());
                    existing.setIsActivity(newCompany.getIsActivity());
                    existing.setCompanyName(newCompany.getCompanyName());
                    return companyRepository.save(existing);
                })
                .orElse(companyRepository.save(newCompany));
    }

}
