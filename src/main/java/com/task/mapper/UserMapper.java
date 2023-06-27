package com.task.mapper;

import com.task.dto.UserDto;
import com.task.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public UserDto toDto(User model) {
        var dto = new UserDto();
        dto.setId(model.getId());
        dto.setAge(model.getAge());
        dto.setBirthday(model.getBirthday());
        dto.setGender(model.getGender());
        dto.setDescription(model.getDescription());
        dto.setFamilyName(model.getFamilyName());
        dto.setFirstName(model.getFirstName());
        dto.setMiddleName(model.getMiddleName());
        dto.setIsBlocked(model.getIsBlocked());
        return dto;
    }

    public User toModel(UserDto dto) {
        return User.builder()
                .id(dto.getId() == null ? UUID.randomUUID() : dto.getId())
                .firstName(dto.getFirstName())
                .familyName(dto.getFamilyName())
                .middleName(dto.getMiddleName())
                .birthday(dto.getBirthday())
                .age(dto.getAge())
                .description(dto.getDescription())
                .gender(dto.getGender())
                .isBlocked(dto.getIsBlocked())
                .build();
    }

}
